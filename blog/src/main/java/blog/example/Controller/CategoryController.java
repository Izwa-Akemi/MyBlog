package blog.example.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.Model.Entity.CategoryEntity;
import blog.example.Model.Service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	//一覧を表示
	@GetMapping("/categoryAll/{adminId}")
	public String getCategoryAll(@PathVariable("adminId")Long adminId,Model model) {
		List<CategoryEntity>categorylist = categoryService.findByAdminId(adminId);
		System.out.println(categorylist);
		model.addAttribute("categorylist",categorylist);

		return "category_all_view.html";
	}
	@GetMapping("/categoryRegister")
	public String geCategoryListRegister() {
		return "category_register_view.html";
	}
	//登録内容を保存
	@PostMapping("/categoryCreate")
	public String register(@RequestParam String categoryName,@RequestParam Long adminId , Model model) {
		categoryService.insert(categoryName, adminId);
		return "redirect:/adminAll";
	}
}
