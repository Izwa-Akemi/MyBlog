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
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		orderDao.save(orderEntity);
	}
	
	public List<OrderEntity> selectMaxOrderId(){
		//daoクラスのfindByItemIdメソッドに値を渡して内容を取得する。
		return orderDao.findByOrderId();
	}

}
