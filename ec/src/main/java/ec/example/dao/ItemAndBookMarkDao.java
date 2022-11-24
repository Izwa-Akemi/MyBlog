package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.example.entity.CartHistoryAndItemEntity;
import ec.example.entity.ItemAndBookMarkEntity;
import ec.example.entity.ItemEntity;

public interface ItemAndBookMarkDao extends JpaRepository<ItemAndBookMarkEntity, Long> {
	@Query(value="select b.user_id AS user_id, i.item_id AS item_id, i.detail AS detail,i.stock AS stock,i.image AS image, i.price AS price, i.item_name AS item_name, b.bookmark_id AS bookmark_id from item as i left outer join bookmark as b on i.item_id=b.item_id",
			nativeQuery = true)
	List<ItemAndBookMarkEntity>findAll();
	
//	@Query(value="select b.user_id userID, i.item_id itemId, i.image image, i.price price, i.item_name itemName, b.bookmark_id bookmarkID from item as i left outer join bookmark as b on i.item_id=b.item_id",
//			nativeQuery = true)
//	ItemAndBookMarkEntity findById();
	
	@Query(value="select b.user_id AS user_id, i.item_id AS item_id, i.detail AS detail,i.stock AS stock,i.image AS image, i.price AS price, i.item_name AS item_name, b.bookmark_id AS bookmark_id from item as i left outer join bookmark as b on i.item_id=b.item_id where i.item_id =?1",
			nativeQuery = true)
	ItemAndBookMarkEntity findByItemId(Long itemId);
	@Query(value="select b.user_id AS user_id, i.item_id AS item_id, i.detail AS detail,i.stock AS stock,i.image AS image, i.price AS price, i.item_name AS item_name, b.bookmark_id AS bookmark_id from item as i left outer join bookmark as b on i.item_id=b.item_id where i.category_id =?1",
			nativeQuery = true)
	List<ItemAndBookMarkEntity> findByCategoryId(int categoryId);
	@Query(value="select b.user_id AS user_id, i.item_id AS item_id, i.detail AS detail,i.stock AS stock,i.image AS image, i.price AS price, i.item_name AS item_name, b.bookmark_id AS bookmark_id from item as i left outer join bookmark as b on i.item_id=b.item_id where i.item_name LIKE ?1",
			nativeQuery = true)
	List<ItemAndBookMarkEntity> findByItemNameLike(String itemName);
	@Query(value="select b.user_id AS user_id, i.item_id AS item_id, i.detail AS detail,i.stock AS stock,i.image AS image, i.price AS price, i.item_name AS item_name, b.bookmark_id AS bookmark_id from item as i left outer join bookmark as b on i.item_id=b.item_id where i.category_id =?1 And i.item_name LIKE ?2",
			nativeQuery = true)
	List<ItemAndBookMarkEntity> findByCategoryIdAndItemNameLike(int categoryId,String itemName);
}
