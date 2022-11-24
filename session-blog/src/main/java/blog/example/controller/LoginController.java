package blog.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.entity.AdminEntity;
import blog.example.model.entity.BlogEntity;
import blog.example.model.service.AdminService;
import blog.example.model.service.BlogService;

@Controller
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	BlogService blogService;
	@Autowired
	HttpSession session;

	//管理者Admin
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "login_view.html";
	}


	@PostMapping("/admin/auth")
	public String adminAuth(@RequestParam String adminEmail,@RequestParam String password,Model model) {
		AdminEntity adminEntity = adminService.selectByAdminEmailAndPassword(adminEmail, password);
		if(adminEntity == null) {
			return "login_view.html";
		}else {
			session.setAttribute("admin",adminEntity);
			List<BlogEntity>blogList = blogService.selectByAll();
		    model.addAttribute("blogList",blogList);
			return "blog_all.html";
		}
	}

}



