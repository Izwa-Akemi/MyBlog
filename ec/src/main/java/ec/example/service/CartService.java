package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CartDao;
import ec.example.entity.CartEntity;
import ec.example.entity.CartHistoryEntity;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;
	
	//コントローラークラスで受け取ったuserIdを基にデータを取得する
	public List<CartEntity> selectByUserId(Long userId){
		//コントローラークラスで受け取ったパラメータをdaoのfindByUserIdメソッドに値を渡して内容を取得する
		return cartDao.findByUserId(userId);
	}
	//内容を保存
	public void insert(Long userId,String cartDate) {
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		cartDao.save(new CartEntity(userId,cartDate));
	}
	//削除
	//コントローラークラスからcartIdを取得する
	public List<CartEntity>deleteCart(Long cartId){
		//daoクラスのメソッドに値を渡して削除をする。
		return cartDao.deleteByCartId(cartId);
	}
}
