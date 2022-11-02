package ec.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ec.example.entity.CartEntity;
import ec.example.entity.CartHistoryEntity;
import ec.example.entity.UserEntity;
import ec.example.service.CartHistoryService;
import ec.example.service.CartService;

@Controller
public class CartController {

	@Autowired
	HttpSession session;
	
	@Autowired
	CartService cartService;
	@Autowired
	CartHistoryService cartHistoryService;

	@GetMapping("/cartadd/{itemId}")
	public String getAddCartPage(@PathVariable Long itemId) {
		session.setAttribute("user",new UserEntity((long) 1));
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		Long userId = userEntity.getUserId();
		List<CartEntity> cart = cartService.selectByUserId(userId);
		//現在の時刻の取得
		Date nowDate  = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cartDate = date.format(nowDate);
		Long cartId;
		if(cart.isEmpty()) {
			cartService.insert(userId, cartDate);
			List<CartEntity>carts = cartService.selectByUserId(userId);
			cartId = carts.get(0).getCartId();
			
		}else {
			cartId = cart.get(0).getCartId();
		}
		//もともとの個数を取得
		List<CartHistoryEntity>currentlist = cartHistoryService.selectByCartIdAndItemId(cartId, itemId);
		int currentNum;
		if(currentlist.isEmpty()) {
			 currentNum =1;
		}else {
			currentNum =currentlist.get(0).getNum() +1;
		}
		cartHistoryService.insert(itemId, currentNum, cartId);
		
		return "cart_list.html";
	}

}