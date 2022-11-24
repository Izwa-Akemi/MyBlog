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

	//登録処理
	public void insert(BookMarkEntity bookMarkEntity) {
		//コントローラークラスで受け取った内容をdaoのsaveメソッドに渡して保存を行う。
		bookMarkDao.save(bookMarkEntity);
	}

	//検索処理
	//コントローラークラスからuserIdとitemIdを取得する
	public List<BookMarkEntity> selectByUserIdAndItemId(Long userId,Long itemId){
		//daoクラスのメソッドに値を渡して検索をする
		return bookMarkDao.findByUserIdAndItemId(userId, itemId);
	}

	//削除
	//コントローラークラスからuserIdとitemIdを取得する
	public List<BookMarkEntity>deleteUserIdAndItemId(Long userId,Long itemId){
		//daoクラスのメソッドに値を渡して削除をする。
		return bookMarkDao.deleteByUserIdAndItemId(userId, itemId);
	}
}
