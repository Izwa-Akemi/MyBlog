package ec.example.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.example.entity.CartHistoryAndItemEntity;
import ec.example.entity.CartHistoryEntity;
import ec.example.entity.ItemEntity;
import ec.example.entity.ItemHistoryEntity;
import ec.example.entity.OrderEntity;
import ec.example.entity.UserEntity;
import ec.example.service.CartHistoryService;
import ec.example.service.ItemHistoryService;
import ec.example.service.ItemService;
import ec.example.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired 
	private CartHistoryService cartHistoryService;
	
	@Autowired 
	ItemHistoryService itemHistoryService;
	@Autowired
	ItemService itemService;
	
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
		List<CartHistoryEntity>cartList = cartHistoryService.selectHistoryByCartId(cartId);
		
		
		Iterator<CartHistoryEntity> hIte = cartList.iterator();
		
		while(hIte.hasNext()){
			CartHistoryEntity che = hIte.next();
			ItemHistoryEntity itemHistoryEntity = new ItemHistoryEntity();
			itemHistoryEntity.setNum(che.getNum());
			itemHistoryEntity.setItemId(che.getItemId());
			itemHistoryEntity.setOrdeId(orders.get(0).getOrderId());
			ItemEntity itemEntity = itemService.selectByItemId(che.getItemId());
			int stock = itemEntity.getStock();
			if(stock < che.getNum()) {
				itemEntity.setStock(0);
				itemService.insert(itemEntity);
				itemHistoryEntity.setNum(stock);
			}else {
				itemEntity.setStock(stock - che.getNum());
				itemService.insert(itemEntity);
			}
			if(che.getNum()>0) {
				itemHistoryService.insert(itemHistoryEntity);
			}
			
		}
		itemHistoryService.deleteCartId(cartId);
		return "thanks.html";
	}

}
