package ec.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.example.entity.CartEntity;
import ec.example.entity.CartHistoryEntity;


@Repository
public interface CartHistoryDao extends JpaRepository<CartHistoryEntity,Long>{
	CartHistoryEntity save(CartHistoryEntity cartHistoryEntity);
	@Query(value="select * from carthistory where cart_id=?1 and item_id=?2 and carthistory_id=(select max(carthistory_id) from carthistory where cart_id=?1 and item_id=?2 )",nativeQuery = true)
	List<CartHistoryEntity> findByCartIdAndItemId(Long cartId,Long itemId);
	List<CartHistoryEntity> findByCartId(Long cartId);
	@Transactional
	List<CartHistoryEntity> deleteByCartId(Long cartId);
}
