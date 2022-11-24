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
		//prefServiceクラスのfindAllメソッドを使用して、一覧の内容を取得する。
		List<PrefEntity>preflist = prefService.findAll();
		//user_register.htmlに対して、preflistをキーに変数で置いたpreflistの内容を画面に渡す。
        model.addAttribute("preflist",preflist);
        //user_register.htmlを表示させる。
		return "user_register.html";
	}
	//登録内容を保存
	@PostMapping("userCreate")
	public String register(@RequestParam String userName,@RequestParam String userEmail,
			@RequestParam String password,@RequestParam int prefId,@RequestParam String zipCode,@RequestParam String address) {
		//userServiceクラスのinsertメソッドに@RequestParamdで受け取ったパラメータを渡す。
		userService.insert(userName, userEmail, password,prefId,zipCode,address);
		//処理が終わったら/userRegisterにリダイレクトをさせる。、
		return "redirect:/userRegister";
	}
}
