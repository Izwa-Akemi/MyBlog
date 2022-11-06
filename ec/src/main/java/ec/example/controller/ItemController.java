package ec.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ec.example.entity.AdminEntity;
import ec.example.entity.CategoryEntity;
import ec.example.entity.ItemEntity;
import ec.example.entity.UserEntity;
import ec.example.service.CategoryService;
import ec.example.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	HttpSession session;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;

	//管理者商品一覧画面
	@GetMapping("/itemmanage")
	public String getItemManegePage(Model model) {
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		model.addAttribute("categoryList",categoryList);
		List<ItemEntity>itemList = itemService.findAllItem();
		model.addAttribute("itemList",itemList);
		return "item_list.html";
	}
	//管理者商品登録画面
	@GetMapping("/productregister")
	public String getItemRegisterPage(Model model) {
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		model.addAttribute("categoryList",categoryList);
		return "product_register.html";
	}

	//管理者商品登録画面
	@PostMapping("/itemcreate")
	public String ItemRegister(@RequestParam String itemName,
			@RequestParam Integer categoryId,
			@RequestParam("image") MultipartFile image,
			@RequestParam Integer price,
			@RequestParam Integer stock,
			@RequestParam String detail,
			@RequestParam Integer active) {
		AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
		Long adminId = adminEntity.getAdminId();
		String fileName = image.getOriginalFilename();
		try {
			File imageFile = new File("./src/main/resources/static/itemImage/"+fileName);
			byte[] bytes = image.getBytes();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageFile));
			out.write(bytes);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		itemService.insert(itemName, categoryId, fileName, price, stock, detail,active,adminId);

		return "redirect:/itemmanage";
	}


	@GetMapping("/searchadminitem")
	@ResponseBody
	public String getSearchAdminItemPage(@RequestParam int categoryId,@RequestParam String itemName) {
		List<ItemEntity> itemList =  itemService.returnSerach(categoryId, itemName);
		return getJsonAdmin(itemList);
	}
	/**
	 * 引数のUserDataオブジェクトをJSON文字列に変換する
	 * @param userDataList UserDataオブジェクトのリスト
	 * @return 変換後JSON文字列
	 */
	private String getJsonAdmin(List<ItemEntity> itemList){
		String retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			retVal = objectMapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}

	@GetMapping("/admin/detail/{itemId}")
	public String getAdminItemDetailPage(@PathVariable Long itemId, Model model) {
		ItemEntity item = itemService.selectByItemId(itemId);
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("item", item);
		return "product_edit.html";

	}

	//管理者商品登録画面
	@PostMapping("/itemupdate")
	public String ItemRegister(@RequestParam Long itemId, @RequestParam String itemName,
			@RequestParam Integer categoryId,
			@RequestParam("image") MultipartFile image,
			@RequestParam Integer price,
			@RequestParam Integer stock,
			@RequestParam String detail,
			@RequestParam Integer active) {
		AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
		Long adminId = adminEntity.getAdminId();
		String fileName = image.getOriginalFilename();
		try {
			File imageFile = new File("./src/main/resources/static/itemImage/"+fileName);
			byte[] bytes = image.getBytes();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageFile));
			out.write(bytes);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		itemService.update(itemId,itemName, categoryId, fileName, price, stock, detail,active,adminId);

		return "redirect:/itemmanage";
	}


	/*--------------------------------------------------------------------------------------------
	 * ユーザー側の操作
	 *
	 */
	//ユーザー側の商品一覧
	@GetMapping("/itemList")
	public String getItemListPage(Model model) {
		List<ItemEntity>itemList = itemService.findAllItem();
		model.addAttribute("itemList",itemList);
		return "index.html";
	}

	@GetMapping("/searchitem")
	@ResponseBody
	public String getSearchItemPage(@RequestParam int categoryId,@RequestParam String itemName) {
		List<ItemEntity> itemList =  itemService.returnSerach(categoryId, itemName);
		return getJson(itemList);
	}
	/**
	 * 引数のUserDataオブジェクトをJSON文字列に変換する
	 * @param userDataList UserDataオブジェクトのリスト
	 * @return 変換後JSON文字列
	 */
	private String getJson(List<ItemEntity> itemList){
		String retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			retVal = objectMapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}

	@GetMapping("/detail/{itemId}")
	public String getDetailPage(@PathVariable Long itemId, Model model) {
		ItemEntity item = itemService.selectByItemId(itemId);
		model.addAttribute("item", item);
		return "product_details.html";

	}

}
