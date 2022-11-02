package ec.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ec.example.entity.AdminEntity;
import ec.example.entity.UserEntity;
import ec.example.service.AdminService;
import ec.example.service.UserService;



@Controller
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	
	//管理者Admin
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "admin_login.html";
	}

	@PostMapping("/adminauth")
	public String adminAuth(@RequestParam(name="adminname") String adminName,@RequestParam String password) {
		AdminEntity adminEntity = adminService.findByAdminNameAndPassword(adminName, password);
		if(adminEntity == null) {
			return "login_view.html";
		}else {
			session.setAttribute("admin",adminEntity);
			return "admin_all_view.html";
		}
	}


	//User
	@GetMapping("/user/login")
	public String getUserLoginPage() {
		return "user_login.html";
	}

	@PostMapping("/userauth")
	public String userAuth(@RequestParam(name="username") String userName,@RequestParam String password) {
		UserEntity userEntity = userService.findByUserNameAndPassword(userName, password);
		if(userEntity == null) {
			return "login_view.html";
		}else {
			session.setAttribute("user",userEntity);
			return "user_all_view.html";
		}
	}


}
