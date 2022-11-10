package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.example.entity.CartHistoryAndItemEntity;


@Repository
public interface CartHistoryAndItemDao extends JpaRepository<CartHistoryAndItemEntity,Long>{
	@Query(value="select i.item_id AS item_id, h.cart_id AS cart_id,h.num AS num,i.item_name AS item_name,i.image AS image,i.stock AS stock,i.price AS price,i.detail AS detail from carthistory h INNER JOIN item i ON h.item_id = i.item_id WHERE h.cart_id=?1",
			nativeQuery = true)
	List<CartHistoryAndItemEntity> findByCartId(Long cartId);
}
