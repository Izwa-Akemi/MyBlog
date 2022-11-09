package ec.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.OrderEntity;
import ec.example.entity.UserEntity;
import ec.example.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired 
	private OrderService orderService;
	@Autowired 
	HttpSession httpSession;
	
	@PostMapping("/order")
	public String OrderRegister(@RequestParam Long cartId) {
		
		UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
		Long userId = userEntity.getUserId();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUserId(userId);
		orderService.insert(orderEntity);
		List<OrderEntity>orders = orderService.selectMaxOrderId();
		System.out.println(orders);
		return "thanks.html";
	}

}
