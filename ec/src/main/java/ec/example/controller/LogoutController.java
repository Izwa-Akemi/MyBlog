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
		//セッションを使用して、現在ログインしているユーザーの情報を取得する。
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		//cartServiceクラスのselectByUserIdメソッドを使用して、該当するユーザー情報を取得する。
		List<CartEntity>cartList = cartService.selectByUserId(userEntity.getUserId());
		//もし、cartListが空でなかった場合
		if(!cartList.isEmpty()) {
			//cartHistoryServiceクラスのdeleteCartHistoryメソッドを使用して、該当するカート履歴情報を削除する。
			cartHistoryService.deleteCartHistory(cartList.get(0).getCartId());
			//cartServiceクラスのdeleteCartメソッドを使用して、該当するカート情報を削除する。
			cartService.deleteCart(cartList.get(0).getCartId());
		}
		//セッションの情報を削除する。
		session.invalidate();
		//user_login.htmlが表示される。
		return "user_login.html";
	}
	//user_idをキーにして，cartからcart_idを取得．
		@GetMapping("/admin/logout")
		public String adminLogout() {
			//セッションの情報を削除する。
			session.invalidate();
			//admin_login.htmlが表示される。
			return "admin_login.html";
		}
}
