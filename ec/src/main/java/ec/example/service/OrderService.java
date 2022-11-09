package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.OrderDao;
import ec.example.entity.OrderEntity;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	public void insert(OrderEntity orderEntity) {
		orderDao.save(orderEntity);
	}
	
	public List<OrderEntity> selectMaxOrderId(){
		return orderDao.findByOrderId();
	}

}
