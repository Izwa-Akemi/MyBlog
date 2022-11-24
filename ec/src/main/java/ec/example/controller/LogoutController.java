package ec.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ec.example.entity.CartEntity;
import ec.example.entity.UserEntity;
import ec.example.service.AdminService;
import ec.example.service.CartHistoryService;
import ec.example.service.CartService;
import ec.example.service.CategoryService;
import ec.example.service.UserService;

@Controller
public class LogoutController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	@Autowired
	CartService cartService;
	@Autowired
	CartHistoryService cartHistoryService;
	
	//user_idをキーにして，cartからcart_idを取得．
	@GetMapping("/logout")
	public String logout() {
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		List<CartEntity>cartList = cartService.selectByUserId(userEntity.getUserId());
		if(!cartList.isEmpty()) {
			cartHistoryService.deleteCartHistory(cartList.get(0).getCartId());
			cartService.deleteCart(cartList.get(0).getCartId());
		}
	
		session.invalidate();
		return "user_login.html";
	}
	//user_idをキーにして，cartからcart_idを取得．
		@GetMapping("/admin/logout")
		public String adminLogout() {
			session.invalidate();
			return "admin_login.html";
		}
}
