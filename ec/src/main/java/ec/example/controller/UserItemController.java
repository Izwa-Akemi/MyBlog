package ec.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.example.entity.CategoryEntity;
import ec.example.entity.ItemAndBookMarkEntity;
import ec.example.entity.ItemEntity;
import ec.example.entity.UserEntity;
import ec.example.service.CategoryService;
import ec.example.service.ItemService;
import ec.example.service.UserItemService;

@Controller
public class UserItemController {
	@Autowired
	HttpSession session;
	@Autowired
	private UserItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserItemService userItemService;

	/*--------------------------------------------------------------------------------------------
	 * ユーザー側の操作
	 *
	 */
	//ユーザー側の商品一覧
	@GetMapping("/itemList")
	public String getItemListPage(Model model) {
		List<ItemAndBookMarkEntity>itemList = userItemService.findAllItem();
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		UserEntity user = (UserEntity) session.getAttribute("user");
		String loginUserName = user.getUserName();
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("itemList",itemList);
		return "index.html";
	}

	@GetMapping("/searchitem")
	@ResponseBody
	public String getSearchItemPage(@RequestParam int categoryId,@RequestParam String itemName) {
		//List<ItemEntity> itemList =  itemService.returnSerach(categoryId, itemName);
       List<ItemAndBookMarkEntity>itemList = itemService.returnSerach(categoryId, itemName);
       System.out.println(itemList);
		return getJson(itemList);
	}
	/**
	 * 引数のUserDataオブジェクトをJSON文字列に変換する
	 * @param userDataList UserDataオブジェクトのリスト
	 * @return 変換後JSON文字列
	 */
	private String getJson(List<ItemAndBookMarkEntity> itemList){
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
		ItemAndBookMarkEntity item = itemService.selectByItemId(itemId);
		UserEntity user = (UserEntity) session.getAttribute("user");
		String loginUserName = user.getUserName();
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("item", item);
		return "product_details.html";

	}
}
