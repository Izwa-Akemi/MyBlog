package ec.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.PrefEntity;
import ec.example.service.PrefService;
import ec.example.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PrefService prefService;
	
	
	//新規登録画面を表示
	@GetMapping("/userRegister")
	public String getRegisterPage(Model model) {
		List<PrefEntity>preflist = prefService.findAll();
        model.addAttribute("preflist",preflist);
		return "user_register.html";
	}
	//登録内容を保存
	@PostMapping("userCreate")
	public String register(@RequestParam String userName,@RequestParam String userEmail,
			@RequestParam String password,@RequestParam int prefId,@RequestParam String zipCode,@RequestParam String address) {
		userService.insert(userName, userEmail, password,prefId,zipCode,address);
		return "redirect:/userRegister";
	}
}
