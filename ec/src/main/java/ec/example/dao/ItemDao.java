package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ec.example.entity.ItemEntity;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long> {
	ItemEntity save(ItemEntity itemEntity);
	List<ItemEntity>findAll();
	ItemEntity findByItemId(Long itemId);
	List<ItemEntity> findByCategoryId(int categoryId);
	List<ItemEntity> findByItemNameLike(String itemName);
	List<ItemEntity> findByCategoryIdAndItemNameLike(int categoryId,String itemName);
}
