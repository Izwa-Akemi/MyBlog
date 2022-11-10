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

	public void insert(ItemHistoryEntity itemHistoryEntity) {
		itemHistoryDao.save(itemHistoryEntity);
	}

	public List<CartHistoryEntity> deleteCartId(Long cartId) {
		return cartHistoryDao.deleteByCartId(cartId);
	}
}
