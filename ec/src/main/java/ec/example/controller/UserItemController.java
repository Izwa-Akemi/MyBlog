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
	//ユーザ情報はセッションで保持しているため、
	//セッション情報を定義させる。
	@Autowired
	HttpSession session;

	/**
	 * 	itemテーブルを操作するためのServiceクラス
	 */
	@Autowired
	private UserItemService itemService;

	/**
	 * categoryテーブルを操作するためのServiceクラス
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * ユーザ用のitemテーブルを操作するためのServicceクラス
	 */
	@Autowired
	private UserItemService userItemService;

	/*--------------------------------------------------------------------------------------------
	 * ユーザー側の操作
	 */
	//ユーザー側の商品一覧
	@GetMapping("/itemList")
	public String getItemListPage(Model model) {
		//itemテーブルから、すべての商品情報を取得する。
		List<ItemAndBookMarkEntity>itemList = userItemService.findAllItem();
		//categoryテーブルから、すべてのカテゴリ情報を取得する。
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		//セッションから、ユーザ情報を取得する。
		UserEntity user = (UserEntity) session.getAttribute("user");
		//取得したユーザ情報から、ユーザIDを取得する。
		String loginUserName = user.getUserName();
		//index.htmlから参照可能なように、ユーザID、カテゴリ情報、商品情報をmodelにセットする。
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("itemList",itemList);
		return "index.html";
	}

	@GetMapping("/searchitem")
	@ResponseBody
	public String getSearchItemPage(@RequestParam int categoryId,@RequestParam String itemName) {
		//List<ItemEntity> itemList =  itemService.returnSerach(categoryId, itemName);
		//カテゴリ情報や、商品名でitemテーブルから商品検索をする。
       List<ItemAndBookMarkEntity>itemList = itemService.returnSerach(categoryId, itemName);
      // System.out.println(itemList);
		//商品情報をJSON文字列に変換したものをHTTP応答として返す。
		return getJson(itemList);
	}
	/**
	 * 引数のitemListをJSON文字列に変換する
	 * @param List<ItemAndBookMarkEntity> itemList
	 * @return 変換後JSON文字列
	 */
	private String getJson(List<ItemAndBookMarkEntity> itemList){
		String retVal = null;
		//JavaオブジェクトをJSON文字列に変換するためのクラスをインスタンス化する。
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			//商品情報リストをJSON文字列に変換する。
			retVal = objectMapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}

	@GetMapping("/detail/{itemId}")
	public String getDetailPage(@PathVariable Long itemId, Model model) {
		//指定の商品IDにより、商品とお気に入りIDを取得する。
		ItemAndBookMarkEntity item = itemService.selectByItemId(itemId);
		//セッションから、ユーザ情報を取得する。
		UserEntity user = (UserEntity) session.getAttribute("user");
		//ユーザ情報から、ユーザ名を取得する。
		String loginUserName = user.getUserName();
		//product_details.htmlから、ユーザ名と商品情報を参照できるようにmodelへセットする。
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("item", item);
		return "product_details.html";

	}
}
