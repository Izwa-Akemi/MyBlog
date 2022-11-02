package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.example.entity.CartEntity;

@Repository
public interface CartDao extends JpaRepository<CartEntity,Long>{
	@Query(value ="select * from cart where user_id=?1 and cart_date=(select max(cart_date) from cart where user_id=?1)",nativeQuery = true)
	List<CartEntity> findByUserId(Long userId);
	CartEntity save(CartEntity cartEntity);

}
