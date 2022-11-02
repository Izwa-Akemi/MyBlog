package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CartHistoryDao;
import ec.example.entity.CartHistoryEntity;


@Service
public class CartHistoryService {
	@Autowired
	CartHistoryDao cartHistoryDao;
	
	public void insert(Long itemId,int num,Long cartId) {
		cartHistoryDao.save(new CartHistoryEntity(itemId,num,cartId));
	}
	
	public List<CartHistoryEntity> selectByCartIdAndItemId(Long cartId,Long itemId){
		return cartHistoryDao.findByCartIdAndItemId(cartId, itemId);
	}
}
