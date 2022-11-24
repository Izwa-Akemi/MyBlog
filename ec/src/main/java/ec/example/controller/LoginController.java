package ec.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ec.example.entity.AdminEntity;
import ec.example.entity.CategoryEntity;
import ec.example.entity.ItemAndBookMarkEntity;
import ec.example.entity.ItemEntity;
import ec.example.entity.UserEntity;
import ec.example.service.AdminService;
import ec.example.service.CategoryService;
import ec.example.service.ItemService;
import ec.example.service.UserItemService;
import ec.example.service.UserService;



@Controller
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserItemService userItemService;
	@Autowired
	private CategoryService categoryService;
	
	//管理者Admin
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		//admin_login.htmlが表示される。
		return "admin_login.html";
	}

	@PostMapping("/adminauth")
	public String adminAuth(@RequestParam(name="adminname") String adminName,@RequestParam String password,Model model) {
		//adminServiceクラスのfindByAdminNameAndPasswordメソッドを使用して、該当するユーザー情報を取得する。
		AdminEntity adminEntity = adminService.findByAdminNameAndPassword(adminName, password);
		//adminEntity　== null
		if(adminEntity == null) {
			//admin_login.htmlが表示される。
			return "admin_login.html";
		}else {
			//adminEntityの内容をsessionに保存する
			session.setAttribute("admin",adminEntity);
			//cartServiceクラスのselecFindAll()を使用して、一覧を取得する。
			List<CategoryEntity>categoryList = categoryService.selecFindAll();
			//categoryListをキーにしてcategoryListをitem_list.htmlに渡す。
			model.addAttribute("categoryList",categoryList);
			//itemServiceクラスのfindAllItem()を使用して、一覧を取得する。
			List<ItemEntity>itemList = itemService.findAllItem();
			//itemListをキーにしてitemListをitem_list.htmlに渡す。
			model.addAttribute("itemList",itemList);
			return "item_list.html";
		}
	}


	//User
	@GetMapping("/user/login")
	public String getUserLoginPage() {
		//user_login.htmllが表示される。
		return "user_login.html";
	}

	@PostMapping("/userauth")
	public String userAuth(@RequestParam(name="username") String userName,@RequestParam String password,Model model) {
		//userServiceクラスのfindByAdminNameAndPasswordメソッドを使用して、該当するユーザー情報を取得する。
		UserEntity userEntity = userService.findByUserNameAndPassword(userName, password);
		//userEntity　== nullの時
		if(userEntity == null) {
			//user_login.htmlが表示される。
			return "user_login.html";
		}else {
			//userEntityの内容をsessionに保存する
			session.setAttribute("user",userEntity);
			//itemServiceクラスのfindAllItem()を使用して、一覧を取得する。
			List<ItemAndBookMarkEntity>itemList = userItemService.findAllItem();
			//cartServiceクラスのselecFindAll()を使用して、一覧を取得する。
			List<CategoryEntity>categoryList = categoryService.selecFindAll();
			//現在ログインしているユーザー情報を取得する
			UserEntity user = (UserEntity) session.getAttribute("user");
			//現在ログインしているユーザー名をloginUserNameとして変数を作成する。
			String loginUserName = user.getUserName();
			//loginUserNameをキーにしてloginUserNameをindex.htmlに渡す。
			model.addAttribute("loginUserName",loginUserName);
			//categoryListをキーにしてcategoryListをindex.htmlに渡す。
			model.addAttribute("categoryList",categoryList);
			//itemListをキーにしてitemListをindex.htmlに渡す。
			model.addAttribute("itemList",itemList);
			return "index.html";
		}
	}


}
