package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.example.entity.CartHistoryAndItemEntity;


@Repository
public interface CartHistoryAndItemDao extends JpaRepository<CartHistoryAndItemEntity,Long>{
	@Query(value="select h.carthistory_id,h.cart_id,i.item_name,i.image,i.stock,i.price,i.detail from carthistory h INNER JOIN item i ON h.item_id = i.item_id WHERE h.cart_id=?1)",nativeQuery = true)
	List<CartHistoryAndItemEntity> findByCartId(Long cartId);
}
