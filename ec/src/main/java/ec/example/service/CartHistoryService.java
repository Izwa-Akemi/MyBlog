package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CartHistoryAndItemDao;
import ec.example.dao.CartHistoryDao;
import ec.example.entity.CartHistoryAndItemEntity;
import ec.example.entity.CartHistoryEntity;


@Service
public class CartHistoryService {
	@Autowired
	CartHistoryDao cartHistoryDao;
	@Autowired
	 CartHistoryAndItemDao  cartHistoryAndItemDao;
	
	//カート内容を保存する
	public void insert(Long itemId,int num,Long cartId) {
		//コントローラークラスで受け取ったパラメータをdaoのsaveメソッドに値を渡して内容を保存する
		cartHistoryDao.save(new CartHistoryEntity(itemId,num,cartId));
	}
	//カート内容を保存する
	public void insert(CartHistoryEntity cartHistoryEntity) {
		cartHistoryDao.save(cartHistoryEntity);
	}
	
	//コントローラークラスで受け取ったcartIdとitemIdを基にデータを取得する
	public List<CartHistoryEntity> selectByCartIdAndItemId(Long cartId,Long itemId){
		//コントローラークラスで受け取ったパラメータをdaoクラスのfindByCartIdAndItemIdメソッドに渡してデータを取得する
		return cartHistoryDao.findByCartIdAndItemId(cartId, itemId);
	}
	
	//カートの内容を更新
	public void update (CartHistoryEntity cartHistoryEntity){
		//コントローラークラスで受け取ったパラメータをdaoのsaveメソッドに値を渡して内容を保存する
		cartHistoryDao.save(cartHistoryEntity);
	}
	
	//カートの内容を取得
	//コントローラークラスで受け取ったcartIdを基にデータを取得する
	public List<CartHistoryAndItemEntity> selectByCartId(Long cartId){
		//コントローラークラスで受け取ったパラメータをdaoクラスのfindByCartIdメソッドに渡してデータを取得する
		return cartHistoryAndItemDao.findByCartId(cartId);
	}
	
	//カートの内容を取得
	//コントローラークラスで受け取ったcartIdを基にデータを取得する
		public List<CartHistoryEntity> selectHistoryByCartId(Long cartId){
			//コントローラークラスで受け取ったパラメータをdaoクラスのfindByCartIdメソッドに渡してデータを取得する
			return cartHistoryDao.findByCartId(cartId);
		}
	//削除
		//コントローラークラスで受け取ったcartIdを基にデータを削除する
		public List<CartHistoryEntity>deleteCartHistory(Long cartId){
			//コントローラークラスで受け取ったパラメータをdaoクラスのdeleteByCartIdメソッドに渡してデータを削除する
			return cartHistoryDao.deleteByCartId(cartId);
		}
//	public List<CartHistoryEntity> selectByCartId(Long cartId){
//		return cartHistoryDao.findByCartId(cartId);
//	}
}
