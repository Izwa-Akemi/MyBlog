package ec.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ec.example.entity.ItemEntity;
import ec.example.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/itemList")
	public String getItemListPage() {
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
