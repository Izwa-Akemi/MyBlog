package ec.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.CartEntity;
import ec.example.entity.CartHistoryAndItemEntity;
import ec.example.entity.CartHistoryEntity;
import ec.example.entity.UserEntity;
import ec.example.service.CartHistoryService;
import ec.example.service.CartService;

@Controller
public class CartController {

	/**
	 * ログイン済みユーザ情報を格納するための
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;

	/**
	 * cartテーブルを操作するための
	 * Serviceクラス
	 */
	@Autowired
	CartService cartService;
	/**
	 * カート内の情報（商品の種類、個数）を扱うための
	 * Serviceクラス
	 */
	@Autowired
	CartHistoryService cartHistoryService;

	/**
	 * カートに商品を追加するための処理です。
	 *
	 - cartテーブルを参照して，user_idに紐づくレコードが無いかチェック
	 - もしあれば，そのcart_idを取得．無ければuser_idを指定して挿入し，新規cart_idを取得．
	 - そのcart_idを指定して，cart_historyに対し，選択中のitem情報＋cart_idを指定して挿入．
	 - cart_historyを参照し，cart_idに紐づくレコードリストを取得．
	 - リストを画面上に出力．
	 * @param itemId カートに追加する商品の商品ID
	 * @param model
	 * @return
	 */
	@GetMapping("/cartadd/{itemId}")
	public String getAddCartPage(@PathVariable Long itemId,Model model) {
		//session.setAttribute("user",new UserEntity((long) 1));
		//セッションからユーザ情報を取得する。
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		//ユーザ情報(UserEntity)からユーザIDを取得する。
		Long userId = userEntity.getUserId();
		//ユーザIDに紐づくカート情報(CartEntity)を、cartテーブルから取得する。
		List<CartEntity> cart = cartService.selectByUserId(userId);
		//現在の時刻の取得
		Date nowDate  = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String cartDate = date.format(nowDate);
		Long cartId;
		//cartテーブルからユーザIDに紐づくカート情報を取得できなかった時は、
		//ログイン後に、初めてカートに商品を追加する場合となる。
		if(cart.isEmpty()) {
			//cartテーブルにユーザIDと現在時刻を追加する。
			cartService.insert(userId, cartDate);
			//ユーザIDを用いて、cartテーブルから、追加したばかりのカート情報を取得する。
			//cartテーブルのcartIdはAuto_Incrementにより自動生成されるため、ここで取得する。
			List<CartEntity>carts = cartService.selectByUserId(userId);
			//ユーザIdに紐づくカートは常に一つであるため、0番目の要素からカートIdを取得する。
			//ログアウト時や、商品購入時に、cart_historyやcartテーブルからカート情報は削除される
			cartId = carts.get(0).getCartId();

		}else {
			//もともとカート情報があれば、そのままカート情報を取得する。
			cartId = cart.get(0).getCartId();
		}

		//もともとのカートにある商品情報を取得
		List<CartHistoryEntity>currentlist = cartHistoryService.selectByCartIdAndItemId(cartId, itemId);
		int currentNum;
		//新規にカートに対して商品を追加する場合（カート内が空の場合）
		if(currentlist.isEmpty()) {
			//カートへの追加ボタンでは1個のみ追加されるため、currentNumに1をセットする。
			currentNum =1;
			//cart_historyテーブルに、カートに追加する商品情報をINSERTする。
			cartHistoryService.insert(itemId, currentNum, cartId);
		}else {
			//カートに既にほかの商品が入っている場合、その商品の個数を+1する。
			currentNum =currentlist.get(0).getNum() +1;
			//cart_historyから、当該商品の情報を取得
			CartHistoryEntity entity = currentlist.get(0);
			//Entityに、更新後の個数をセットする。
			entity.setNum(currentNum);
			//cart_historyテーブルに更新をかける。cart_history_idが設定されているので、
			//INSERTではなくUPDATEがかかるようになっている。
			cartHistoryService.update(entity);

		}
		//カートIDを指定して、cart_historyテーブルから商品情報のリストを取得する。
		List<CartHistoryAndItemEntity>newList = cartHistoryService.selectByCartId(cartId);
		//List<CartHistoryEntity>newList = cartHistoryService.selectByCartId(cartId);

		//セッションから、ユーザ情報を取得する。
		UserEntity user = (UserEntity) session.getAttribute("user");
		//ログインユーザ名を取得する。
		String loginUserName = user.getUserName();
		//cart_list.htmlに、ログインユーザ名、カート内の商品情報リスト、カートIDをセットして、
		//cart_list.htmlから参照可能にする。
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("newList", newList);
		model.addAttribute("cartId", cartId);
		
		return "cart_list.html";
	}
	
	@PostMapping("/cartchange")
		public String chagCart(@RequestParam int num,@RequestParam Long itemId,@RequestParam Long cartId) {
		// cart_historyテーブルから、カートIDと商品IDを指定して、カート内の商品情報を取得する。
		CartHistoryEntity cartHistoryEntity = cartHistoryService.selectByCartIdAndItemId(cartId, itemId).get(0);
		//商品情報に対して、個数を更新させる。
		 cartHistoryEntity.setNum(num);
		 //そしてcart_historyテーブルへ、商品情報の更新をする。
		 cartHistoryService.insert(cartHistoryEntity);
		 return "redirect:/cartnotadd/"+itemId;
		
	}
	
	@GetMapping("/cartnotadd/{itemId}")
	public String getCartnotAddPage(@PathVariable Long itemId,Model model) {
		//session.setAttribute("user",new UserEntity((long) 1));
		//セッションから、ユーザ情報を取得する。
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		//取得したユーザ情報から、ユーザIDを取得する。
		Long userId = userEntity.getUserId();
		//ユーザIDに紐づくカート情報(CartEntity)を、cartテーブルから取得する。
		List<CartEntity> cart = cartService.selectByUserId(userId);
		//現在の時刻の取得
		Date nowDate  = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cartDate = date.format(nowDate);
		Long cartId;
		//cartテーブルからユーザIDに紐づくカート情報を取得できなかった時は、
		//ログイン後に、初めてカートに商品を追加する場合となる。
		if(cart.isEmpty()) {
			//cartテーブルにユーザIDと現在時刻を追加する。
			cartService.insert(userId, cartDate);
			//ユーザIDを用いて、cartテーブルから、追加したばかりのカート情報を取得する。
			//cartテーブルのcartIdはAuto_Incrementにより自動生成されるため、ここで取得する。
			List<CartEntity>carts = cartService.selectByUserId(userId);
			//ユーザIdに紐づくカートは常に一つであるため、0番目の要素からカートIdを取得する。
			//ログアウト時や、商品購入時に、cart_historyやcartテーブルからカート情報は削除される
			cartId = carts.get(0).getCartId();

		}else {
			//もともとカート情報があれば、そのままカート情報を取得する。
			cartId = cart.get(0).getCartId();
		}
		//		- cartテーブルを参照して，user_idに紐づくレコードが無いかチェック--OK
		//	    - もしあれば，そのcart_idを取得．無ければuser_idを指定して挿入し，新規cart_idを取得．--OK
		//	    - そのcart_idを指定して，cart_historyに対し，選択中のitem情報＋cart_idを指定して挿入．
		//	    - cart_historyを参照し，cart_idに紐づくレコードリストを取得．
		//	    - リストを画面上に出力．

		//もともとのカートにある商品情報を取得
		List<CartHistoryEntity>currentlist = cartHistoryService.selectByCartIdAndItemId(cartId, itemId);
		List<CartHistoryAndItemEntity>newList = cartHistoryService.selectByCartId(cartId);
		//List<CartHistoryEntity>newList = cartHistoryService.selectByCartId(cartId);

		//セッションから、ユーザ情報を取得する。
		UserEntity user = (UserEntity) session.getAttribute("user");
		//ログインユーザ名を取得する。
		String loginUserName = user.getUserName();
		//cart_list.htmlに、ログインユーザ名、カート内の商品情報リスト、カートIDをセットして、
		//cart_list.htmlから参照可能にする。
		model.addAttribute("loginUserName",loginUserName);
		model.addAttribute("newList", newList);
		model.addAttribute("cartId", cartId);
		
		return "cart_list.html";
	}
	
	

}
