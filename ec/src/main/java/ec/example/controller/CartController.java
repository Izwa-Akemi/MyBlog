package ec.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.CartEntity;
import ec.example.entity.CartHistoryAndItemEntity;
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
	public String getAddCartPage(@PathVariable Long itemId,Model model) {
		//session.setAttribute("user",new UserEntity((long) 1));
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
		//		- cartテーブルを参照して，user_idに紐づくレコードが無いかチェック--OK
		//	    - もしあれば，そのcart_idを取得．無ければuser_idを指定して挿入し，新規cart_idを取得．--OK
		//	    - そのcart_idを指定して，cart_historyに対し，選択中のitem情報＋cart_idを指定して挿入．
		//	    - cart_historyを参照し，cart_idに紐づくレコードリストを取得．
		//	    - リストを画面上に出力．



		//もともとの個数を取得
		List<CartHistoryEntity>currentlist = cartHistoryService.selectByCartIdAndItemId(cartId, itemId);
		int currentNum;
		if(currentlist.isEmpty()) {
			currentNum =1;
			cartHistoryService.insert(itemId, currentNum, cartId);
		}else {
			currentNum =currentlist.get(0).getNum() +1;
			CartHistoryEntity entity = currentlist.get(0);
			entity.setNum(currentNum);
			cartHistoryService.update(entity);

		}
		List<CartHistoryAndItemEntity>newList = cartHistoryService.selectByCartId(cartId);
		//List<CartHistoryEntity>newList = cartHistoryService.selectByCartId(cartId);
	
		UserEntity user = (UserEntity) session.getAttribute("user");
		String loginUserName = user.getUserName();
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("newList", newList);
		model.addAttribute("cartId", cartId);
		
		return "cart_list.html";
	}
	
	@PostMapping("/cartchange")
		public String chagCart(@RequestParam int num,@RequestParam Long itemId,@RequestParam Long cartId) {
		
		CartHistoryEntity cartHistoryEntity = cartHistoryService.selectByCartIdAndItemId(cartId, itemId).get(0);	
		 cartHistoryEntity.setNum(num);
		 cartHistoryService.insert(cartHistoryEntity);
		 return "redirect:/cartnotadd/"+itemId;
		
	}
	
	@GetMapping("/cartnotadd/{itemId}")
	public String getCartnotAddPage(@PathVariable Long itemId,Model model) {
		//session.setAttribute("user",new UserEntity((long) 1));
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
		//		- cartテーブルを参照して，user_idに紐づくレコードが無いかチェック--OK
		//	    - もしあれば，そのcart_idを取得．無ければuser_idを指定して挿入し，新規cart_idを取得．--OK
		//	    - そのcart_idを指定して，cart_historyに対し，選択中のitem情報＋cart_idを指定して挿入．
		//	    - cart_historyを参照し，cart_idに紐づくレコードリストを取得．
		//	    - リストを画面上に出力．



		//もともとの個数を取得
		List<CartHistoryEntity>currentlist = cartHistoryService.selectByCartIdAndItemId(cartId, itemId);
		List<CartHistoryAndItemEntity>newList = cartHistoryService.selectByCartId(cartId);
		//List<CartHistoryEntity>newList = cartHistoryService.selectByCartId(cartId);
	
		UserEntity user = (UserEntity) session.getAttribute("user");
		String loginUserName = user.getUserName();
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("newList", newList);
		model.addAttribute("cartId", cartId);
		
		return "cart_list.html";
	}
	
	

}
