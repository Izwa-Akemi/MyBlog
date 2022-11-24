package ec.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.example.entity.BookMarkEntity;
import ec.example.entity.CartHistoryEntity;

public interface BookMarkDao extends JpaRepository<BookMarkEntity, Long> {
	BookMarkEntity save(BookMarkEntity bookMarkEntity);
	List<BookMarkEntity> findByUserId(Long userId);
	List<BookMarkEntity> findByUserIdAndItemId(Long cartId,Long itemId);
	@Transactional
	List<BookMarkEntity> deleteByUserIdAndItemId(Long cartId,Long itemId);
}
