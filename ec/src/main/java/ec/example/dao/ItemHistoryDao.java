package ec.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.example.entity.ItemHistoryEntity;

public interface ItemHistoryDao extends JpaRepository<ItemHistoryEntity, Long> {
	ItemHistoryEntity save(ItemHistoryEntity itemEntity);
}
