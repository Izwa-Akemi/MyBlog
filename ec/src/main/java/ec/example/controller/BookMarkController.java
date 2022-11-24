package ec.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.example.entity.BookMarkEntity;
import ec.example.entity.ItemEntity;
import ec.example.entity.UserEntity;
import ec.example.service.BookMarkService;

@Controller
public class BookMarkController {
	@Autowired
	HttpSession httpSession;
	@Autowired
	BookMarkService bookMarkService;
	
	@GetMapping("/addfavorite")
	@ResponseBody
	public String getAddFavorite(@RequestParam Long itemId) {
		int addFlg = 0;
		Long userId = ((UserEntity) httpSession.getAttribute("user")).getUserId();
		List<BookMarkEntity>bookMarkList = bookMarkService.selectByUserIdAndItemId(userId, itemId);
		if(bookMarkList.isEmpty()) {
			BookMarkEntity book = new BookMarkEntity();
			book.setItemId(itemId);
			book.setUserId(userId);
			bookMarkService.insert(book);
			addFlg = 1;
		}else {
			bookMarkService.deleteUserIdAndItemId(userId, itemId);
		}
		return getJson(addFlg);
	}
	/**
	 * 引数のUserDataオブジェクトをJSON文字列に変換する
	 * @param userDataList UserDataオブジェクトのリスト
	 * @return 変換後JSON文字列
	 */
	private String getJson(int addFlg){
		String retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			retVal = objectMapper.writeValueAsString(addFlg);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}


}
