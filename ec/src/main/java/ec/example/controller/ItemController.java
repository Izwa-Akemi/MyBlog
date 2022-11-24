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


    /**
     *
     * admin情報をセッションから取得するために、
     * HttpSessionを取得可能にしておきます。
     *
     */
	@Autowired
	HttpSession session;

    /**
     * 商品テーブルにアクセスして操作するため、ItemServiceクラス
	 * を使えるようにしておきます。
     */
    @Autowired
	private ItemService itemService;

	/**
	 * 商品のカテゴリにアクセスして操作するため、CategoryServiceクラス
	 * を使えるようにしておきます。
	 */
	@Autowired
	private CategoryService categoryService;

	//管理者商品一覧画面
	@GetMapping("/itemmanage")
	public String getItemManegePage(Model model) {
		//categoryテーブルから、すべてのカテゴリ情報を取得する。
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		//item_list.htmlにすべてのカテゴリ情報を表示可能にするため、
		//modelへ、categoryテーブルから取得した情報をセットする。
		model.addAttribute("categoryList",categoryList);
		//すべての商品情報を取得する。
		List<ItemEntity>itemList = itemService.findAllItem();
		//item_list.htmlに、すべての情報を表示させるために、itemテーブル
		//から取得した商品情報をセットする。
		model.addAttribute("itemList",itemList);
		return "item_list.html";
	}
	//管理者商品登録画面
	@GetMapping("/productregister")
	public String getItemRegisterPage(Model model) {
		//categoryテーブルから、すべてのカテゴリ情報を取得する。
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		//product_register.htmlにすべてのカテゴリ情報を表示可能にするため、
		//modelへ、categoryテーブルから取得した情報をセットする。
		model.addAttribute("categoryList",categoryList);
		return "product_register.html";
	}

	//管理者商品登録画面

	/**
	 *
	 * @param itemName 商品名
	 * @param categoryId 登録する商品カテゴリID
	 * @param image  登録する画像ファイル
	 * @param price 登録する価格
	 * @param stock 登録する在庫数
	 * @param detail 登録する詳細文章
	 * @param active 商品を取り扱いOKとするかどうか
	 * @return
	 */
	@PostMapping("/itemcreate")
	public String ItemRegister(@RequestParam String itemName,
			@RequestParam Integer categoryId,
			@RequestParam("image") MultipartFile image,
			@RequestParam Integer price,
			@RequestParam Integer stock,
			@RequestParam String detail,
			@RequestParam Integer active) {
		//セッションから、adminEntityを取得する。
		AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
		//adminEntityから、adminIdを取得する。
		Long adminId = adminEntity.getAdminId();
		//アップロードされた画像データから、ファイル名を取得する。
		String fileName = image.getOriginalFilename();
		try {
			//サーバ上で保存する画像のpath(場所）を決める。
			File imageFile = new File("./src/main/resources/static/itemImage/"+fileName);
			//アップロードされた画像ファイルのバイナリデータを取得する。これは、ファイル書き出しの
			//ための準備となる。
			byte[] bytes = image.getBytes();
			//ファイル書き出しのためのバッファを生成する。
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageFile));
			//ファイル書き出し処理
			out.write(bytes);
			//バッファをcloseすることにより、ファイル書き出しを正常終了させる。
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		//送信された商品情報を、実際にitemテーブルへ追加させる。
		itemService.insert(itemName, categoryId, fileName, price, stock, detail,active,adminId);

		return "redirect:/itemmanage";
	}

	/**
	 * 商品管理画面において、商品検索を行う処理です。
	 * @param categoryId 検索するための商品カテゴリ
	 * @param itemName 検索するための商品名
	 * @return
	 */
	@GetMapping("/searchadminitem")
	@ResponseBody
	public String getSearchAdminItemPage(@RequestParam int categoryId,@RequestParam String itemName) {
		//入力された、カテゴリIDと商品名を使って商品検索を行う。
		List<ItemEntity> itemList =  itemService.returnSerach(categoryId, itemName);
		return getJsonAdmin(itemList);
	}
	
	private String getJsonAdmin(List<ItemEntity> itemList){
		String retVal = null;
		//JavaのオブジェクトをJSON文字列に変換するためのクラスを用意する。
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			//商品リスト(ItemEntityのリスト）をJSONの文字列へ変換する。
			retVal = objectMapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		//変換されたJSON文字列を返す。
		//getSearchAdminItemPageメソッドがこのJSON文字列をHTTP応答として
		//返すことになる。
		return retVal;
	}

	/**
	 * 管理側での商品詳細ページの表示処理
	 * @param itemId 指定の商品ID
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/detail/{itemId}")
	public String getAdminItemDetailPage(@PathVariable Long itemId, Model model) {
		//商品IDを指定して、itemテーブルから指定の商品情報(ItemEntity)を取得する。
		ItemEntity item = itemService.selectByItemId(itemId);
		//Categoryテーブルから、すべてのカテゴリ情報(CategoryEntityのリスト）を取得する。
		List<CategoryEntity>categoryList = categoryService.selecFindAll();
		//取得したCategoryリストをmodelにセットして、product_edit.htmlにて表示可能にする。
		model.addAttribute("categoryList",categoryList);
		//取得した商品リストをmodelにセットして、product_edit.htmlにて表示可能にする。
		model.addAttribute("item", item);
		return "product_edit.html";

	}

	//管理者商品編集画面
	@PostMapping("/itemupdate")
	public String ItemRegister(@RequestParam Long itemId, @RequestParam String itemName,
			@RequestParam Integer categoryId,
			@RequestParam("image") MultipartFile image,
			@RequestParam Integer price,
			@RequestParam Integer stock,
			@RequestParam String detail,
			@RequestParam Integer active) {
		//セッションにセットされているAdmin情報(AdminEntity)を取得する。
		AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
		//セッションから取得したAdminEntityから、adminIdを取得する。
		Long adminId = adminEntity.getAdminId();
		//アップロードされた画像ファイルのファイル名を取得する。
		String fileName = image.getOriginalFilename();
		try {
			//画像ファイルの保存先を指定する。
			File imageFile = new File("./src/main/resources/static/itemImage/"+fileName);
			//画像ファイルからバイナリデータを取得する。
			byte[] bytes = image.getBytes();
			//画像を保存（書き出し）するためのバッファを用意する。
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageFile));
			//画像ファイルの書き出しする。
			out.write(bytes);
			//バッファを閉じることにより、書き出しを正常終了させる。
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		//入力された商品情報によって、itemテーブルに対して更新を行う。
		itemService.update(itemId,itemName, categoryId, fileName, price, stock, detail,active,adminId);

		return "redirect:/itemmanage";
	}

	//管理者在庫リスト画面
	@GetMapping("/inventorylist")
	public String getInventoryListPage(Model model) {
		//itemテーブルから、すべての商品情報を取得する。
	    List<ItemEntity> itemList = itemService.findAllItem();
		//取得した商品情報リストをmodelにセットして、product_list.htmlにて表示可能にする。
	    model.addAttribute("itemList",itemList);
		return "product_list.html";
	}
	//在庫更新
	@PostMapping("/inventory/update")
	public String updateInventoryList(@RequestParam Long itemId,@RequestParam Integer stock) {
		//セッションから、AdminEntityを取得する。
		AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
		//取得したAdminEntityからadminIdを取得する。
		Long adminId = adminEntity.getAdminId();
		//指定した商品IDにより、itemテーブルから目的の商品を取得する。
		ItemEntity itemEntity = itemService.selectByItemId(itemId);
		//以降は、itemEntityに対して新しい情報を設定する。
		//管理者IDの設定
		itemEntity.setAdminId(adminId);
		//在庫数の更新設定
		itemEntity.setStock(stock);
		//itemテーブルに対して、更新をかける。この場合、ItmeEntityにitemIdが
		//設定されているので、INSERTではなくUPDATEの処理がなされる。
	    itemService.insert(itemEntity);
		return "redirect:/inventorylist";
	}


}
