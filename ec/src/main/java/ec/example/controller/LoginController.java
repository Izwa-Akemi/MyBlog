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
import ec.example.entity.ItemEntity;
import ec.example.entity.UserEntity;
import ec.example.service.AdminService;
import ec.example.service.CategoryService;
import ec.example.service.ItemService;
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
	private CategoryService categoryService;
	
	//管理者Admin
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "admin_login.html";
	}

	@PostMapping("/adminauth")
	public String adminAuth(@RequestParam(name="adminname") String adminName,@RequestParam String password,Model model) {
		AdminEntity adminEntity = adminService.findByAdminNameAndPassword(adminName, password);
		if(adminEntity == null) {
			return "login_view.html";
		}else {
			session.setAttribute("admin",adminEntity);
			List<CategoryEntity>categoryList = categoryService.selecFindAll();
			model.addAttribute("categoryList",categoryList);
			List<ItemEntity>itemList = itemService.findAllItem();
			model.addAttribute("itemList",itemList);
			return "item_list.html";
		}
	}


	//User
	@GetMapping("/user/login")
	public String getUserLoginPage() {
		return "user_login.html";
	}

	@PostMapping("/userauth")
	public String userAuth(@RequestParam(name="username") String userName,@RequestParam String password,Model model) {
		UserEntity userEntity = userService.findByUserNameAndPassword(userName, password);
		if(userEntity == null) {
			return "user_login.html";
		}else {
			session.setAttribute("user",userEntity);
			List<ItemEntity>itemList = itemService.findAllItem();
			List<CategoryEntity>categoryList = categoryService.selecFindAll();
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("itemList",itemList);
			return "index.html";
		}
	}


}
