package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.service.AdminService;

@Controller
public class RegisterController {
	
	    @Autowired
	    private AdminService adminService;
	    //新規登録画面を表示
	    @GetMapping("/admin/register")
	    public String getRegisterPage() {
	        return "admin_register.html";
	    }
	    //登録内容を保存
	    @PostMapping("/admin/create")
	    public String register(@RequestParam String adminName,@RequestParam String adminEmail,@RequestParam String password) {
	        adminService.insert(adminName, adminEmail, password);
	        return "redirect:/admin/register";
	    }
	  
}
