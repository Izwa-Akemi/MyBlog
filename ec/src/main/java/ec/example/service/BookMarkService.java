package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.BookMarkDao;
import ec.example.entity.BookMarkEntity;
import ec.example.entity.CartHistoryEntity;

@Service
public class BookMarkService {

	@Autowired
	private BookMarkDao bookMarkDao;

	public void insert(BookMarkEntity bookMarkEntity) {
		bookMarkDao.save(bookMarkEntity);
	}

	public List<BookMarkEntity> selectByUserIdAndItemId(Long userId,Long itemId){
		return bookMarkDao.findByUserIdAndItemId(userId, itemId);
	}

	//削除
	public List<BookMarkEntity>deleteUserIdAndItemId(Long userId,Long itemId){
		return bookMarkDao.deleteByUserIdAndItemId(userId, itemId);
	}
}
