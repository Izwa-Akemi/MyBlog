package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.example.entity.OrderEntity;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {
	OrderEntity save(OrderEntity orderEntity);
	@Query(value ="select * from orders order by order_id desc limit 1",nativeQuery = true)
	List<OrderEntity> findByOrderId();
}
