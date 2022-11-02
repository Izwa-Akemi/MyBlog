package blog.example.controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import blog.example.config.WebSecurityConfig;
import blog.example.model.entity.BlogEntity;
import blog.example.model.entity.CategoryEntity;
import blog.example.model.entity.UserEntity;
import blog.example.model.service.BlogService;
import blog.example.model.service.CategoryService;
import blog.example.model.service.UserService;
import lombok.NonNull;


@Controller
public class BlogController {

	@Autowired
	private UserService userService;
	@Autowired
	BlogService blogService;
	@Autowired
	CategoryService categoryServie;



	@GetMapping("/blogAll")
	public String getLoginPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		UserEntity user = userService.selectById(userEmail);
		String userName = user.getUserName();
		Long userId = user.getUserId();
		List<BlogEntity>blogList = blogService.selectByUserId(userId);
		model.addAttribute("blogList",blogList);	 
		return "blog_all_view.html";
	}

	@GetMapping("/blogcreate")
	public String getBlogCreatePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		UserEntity user = userService.selectById(userEmail);
		Long userId = user.getUserId();
		//カテゴリー一覧を取得
		List<CategoryEntity>categoryList = categoryServie.findByAll();
		model.addAttribute("userId",userId);
		model.addAttribute("categoryList",categoryList);

		return "blog_register_view.html";
	}

	//登録内容を保存
	@PostMapping("/blogRegister")
	public String register(@RequestParam String blogTitle,@RequestParam("blogImage") MultipartFile blogImage,@RequestParam String categoryName,@RequestParam String message,@RequestParam Long userId) {

		String fileName = blogImage.getOriginalFilename();

		try {
			File blogFile = new File("./blog-image/"+fileName);
			byte[] bytes = blogImage.getBytes();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(blogFile));
			out.write(bytes);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		blogService.insert(blogTitle, fileName, categoryName, message, userId);

		return "redirect:/blogAll";
	}

	@GetMapping("/blogDetail/{blogId}")
	public String getBlogDetailPage(@PathVariable Long blogId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		UserEntity user = userService.selectById(userEmail);
		String userName = user.getUserName();
		Long userId = user.getUserId();
		List<CategoryEntity>categoryList = categoryServie.findByAll();
		BlogEntity blogs = blogService.selectByBlogId(blogId);
		model.addAttribute("userId",userId);
		model.addAttribute("blogs",blogs);	
		model.addAttribute("categoryList",categoryList);
		return "blog_detail_view.html";
	}

	//登録内容を修正
	@PostMapping("/blogUpdate")
	public String updateData(@RequestParam Long blogId,@RequestParam String blogTitle,@RequestParam("blogImage") MultipartFile blogImage,@RequestParam String categoryName,@RequestParam String message,@RequestParam Long userId) {

		String fileName = blogImage.getOriginalFilename();

		try {
			File blogFile = new File("./blog-image/"+fileName);
			byte[] bytes = blogImage.getBytes();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(blogFile));
			out.write(bytes);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		blogService.update(blogId, blogTitle, fileName, categoryName, message, userId);

		return "redirect:/blogAll";
	}

	@GetMapping("/blog")
	public String getBlogUserPage(Model model) {
		List<BlogEntity>blogList = blogService.selectByAll();
		model.addAttribute("blogList",blogList);	
		List<CategoryEntity>categoryList = categoryServie.findByAll();
		model.addAttribute("categoryList",categoryList);
		return "index.html";
	}
	
	@GetMapping("/blogUserDetail/{blogId}")
	public String getBlogUserDetailPage(@PathVariable Long blogId, Model model) {
		BlogEntity blogs = blogService.selectByBlogId(blogId);
		model.addAttribute("blogs",blogs);	
		return "blog.html";
	}
}
