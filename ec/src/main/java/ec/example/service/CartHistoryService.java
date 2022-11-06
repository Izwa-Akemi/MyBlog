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
	 
	public void insert(Long itemId,int num,Long cartId) {
		cartHistoryDao.save(new CartHistoryEntity(itemId,num,cartId));
	}
	
	public List<CartHistoryEntity> selectByCartIdAndItemId(Long cartId,Long itemId){
		return cartHistoryDao.findByCartIdAndItemId(cartId, itemId);
	}
	
	//カートの内容を更新
	public void update (CartHistoryEntity cartHistoryEntity){
		cartHistoryDao.save(cartHistoryEntity);
	}
	
	//カートの内容を取得
	public List<CartHistoryAndItemEntity> selectByCartId(Long cartId){
		return cartHistoryAndItemDao.findByCartId(cartId);
	}
//	public List<CartHistoryEntity> selectByCartId(Long cartId){
//		return cartHistoryDao.findByCartId(cartId);
//	}
}
