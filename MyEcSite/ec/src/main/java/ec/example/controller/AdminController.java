package ec.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.AdminEntity;
import ec.example.service.AdminService;



@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    //新規登録画面を表示
    @GetMapping("/adminRegister")
    public String getRegisterPage() {
        return "admin_register.html";
    }
    //登録内容を保存
    @PostMapping("adminCreate")
    public String register(@RequestParam String adminName,@RequestParam String adminEmail,@RequestParam String password) {
        adminService.insert(adminName, adminEmail, password);
        return "redirect:/adminRegister";
    }
  
}
