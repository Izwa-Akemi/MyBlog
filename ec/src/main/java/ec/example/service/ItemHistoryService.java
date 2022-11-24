package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CartHistoryDao;
import ec.example.dao.ItemHistoryDao;
import ec.example.entity.CartHistoryEntity;
import ec.example.entity.ItemHistoryEntity;
import ec.example.entity.OrderEntity;

@Service
public class ItemHistoryService {
	@Autowired
	private ItemHistoryDao itemHistoryDao;
	@Autowired
	private CartHistoryDao cartHistoryDao;
	
	//内容を保存
	public void insert(ItemHistoryEntity itemHistoryEntity) {
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		itemHistoryDao.save(itemHistoryEntity);
	}

	//削除
		//コントローラークラスからcartIdを取得する
	public List<CartHistoryEntity> deleteCartId(Long cartId) {
		//daoクラスのメソッドに値を渡して削除をする。
		return cartHistoryDao.deleteByCartId(cartId);
	}
}
