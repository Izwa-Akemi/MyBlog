package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CartDao;
import ec.example.entity.CartEntity;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;
	
	public List<CartEntity> selectByUserId(Long userId){
		return cartDao.findByUserId(userId);
	}
	
	public void insert(Long userId,String cartDate) {
		cartDao.save(new CartEntity(userId,cartDate));
	}
}
