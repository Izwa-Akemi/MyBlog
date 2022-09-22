package myblog.akemi.Controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myblog.akemi.Model.Entity.AdminEntity;
import myblog.akemi.Model.Service.AdminService;


@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	//新規登録画面を表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "admin_register_view.html";
	}

	//登録内容を保存
	@PostMapping("adminCreate")
	public String register(@RequestParam String adminName,@RequestParam String adminEmail,String password, Model model) {
		adminService.insert(adminName, adminEmail, password);
		return "redirect:/register";
	}
	//一覧を表示
	@GetMapping("/adminAll")
	public String getAdminAll(Model model) {
		List<AdminEntity>adminlist = adminService.findAll();
		model.addAttribute("adminlist",adminlist);
		return "admin_all_view.html";
	}

	//編集画面へ遷移
	@PostMapping("/edit")
	public String getEditpage(@RequestParam Long adminId,Model model) {
		Optional<AdminEntity> adminNumber = adminService.selectById(adminId);
		AdminEntity adminEntity = adminNumber.get();
		model.addAttribute("adminId",adminEntity.getAdminId());
		model.addAttribute("adminName",adminEntity.getAdminName());
		model.addAttribute("adminEmail",adminEntity.getAdminEmail());
		model.addAttribute("password",adminEntity.getPassword());
		return "admin_edit_view.html";
	}

	//編集内容を更新
//	@PostMapping("/update")
//	public String updateAdmin(@RequestParam Long adminId, @RequestParam String adminName,@RequestParam String adminEmail,@RequestParam String password) {
//		adminService.update(adminId,adminName, adminEmail, password);
//		return "redirect:/adminAll";
//
//	}
	
	//内容を削除
	@PostMapping("/delete")
	public String deleteAdmin(@RequestParam Long adminId) {
		adminService.delete(adminId);
		return "redirect:/adminAll";

	}


}

