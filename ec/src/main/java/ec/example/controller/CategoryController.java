package ec.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.CategoryEntity;
import ec.example.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;


	//登録内容を一覧表示
	@GetMapping("/categoryAll")
	public String getCategoryAllPage(Model model) {
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		model.addAttribute("categoryList",categoryList);
		return "category_all.html";
	}


	//新規登録画面を表示
	@GetMapping("/categoryRegister")
	public String getCategoryRegisterPage(Model model) {
		return "category_register.html";
	}
	//登録内容を保存
	@PostMapping("/categoryCreate")
	public String CategoryRegister(@RequestParam String categoryName,@RequestParam int active) {
		categoryService.insert(categoryName,active);
		return "redirect:/categoryAll";
	}

	//編集画面を表示
	@GetMapping("/category/{categoryId}")
	public String getCategoryEditPage(@PathVariable Long categoryId,Model model) {
		CategoryEntity category = categoryService.selectByCategoryId(categoryId);
		model.addAttribute("category",category);
	    
		return "category_edit.html";
	}

	//更新内容を保存
	@PostMapping("/categoryUpdate")
	public String CategoryUpdate(@RequestParam Long categoryId,@RequestParam String categoryName,@RequestParam int active) {
		categoryService.update(categoryId,categoryName,active);
		return "redirect:/categoryAll";
	}
}
