package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.ItemAndBookMarkDao;
import ec.example.entity.ItemAndBookMarkEntity;

@Service
public class UserItemService {
	@Autowired
	ItemAndBookMarkDao itemDao;
	//検索処理
		//コントローラークラスで受け取ったcategoryIdとitemNameを基にデータを取得する
	public List<ItemAndBookMarkEntity> returnSerach(int categoryId,String itemName){
		//categoryIdが0でitemNameが空白だった場合
		if(categoryId == 0 && itemName.equals("")) {
			//daoクラスのfindAllメソッドを使用してitem一覧を取得する
			return itemDao.findAll();
		}
		//categoryIdが0の場合
		if(categoryId == 0) {
			//daoクラスのfindByItemNameLikeメソッドに値を渡して内容を取得する。
			return itemDao.findByItemNameLike("%"+itemName+"%");
		}
		//itemNameが空の場合
		if(itemName.equals("")) {
			//daoクラスのfindByCategoryIdメソッドに値を渡して内容を取得する。
			return itemDao.findByCategoryId(categoryId);
		}else {
			//daoクラスのfindByCategoryIdAndItemNameLikeメソッドに値を渡して内容を取得する。
			return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
		}
	}
	
	//カテゴリーを探す
	//コントローラークラスで受け取ったcategoryIdを基にデータを取得する
	public List<ItemAndBookMarkEntity> selectByCategoryId(int categoryId){
		//daoクラスのfindByCategoryIdメソッドに値を渡して内容を取得する。
		return itemDao.findByCategoryId(categoryId);
	}
	//名前を探す
	//コントローラークラスで受け取ったitemNameを基にデータを取得する
	public List<ItemAndBookMarkEntity> selectByItemName(String itemName){
		return itemDao.findByItemNameLike("%"+itemName+"%");
	}
	//カテゴリーと名前両方を探す
	//コントローラークラスで受け取ったcategoryIdとitemNameを基にデータを取得する
	public List<ItemAndBookMarkEntity> selectByItemNameAndCategoryId(int categoryId,String itemName){
		return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
	}
	//コントローラークラスで受け取ったitemIdを基にデータを取得する
	public ItemAndBookMarkEntity selectByItemId(Long itemId){
		return itemDao.findByItemId(itemId);
	}
	
	//商品一覧を取得
	public List<ItemAndBookMarkEntity> findAllItem(){
		//daoクラスのfindAllメソッドを使用してデータ一覧を取得する
		return itemDao.findAll();
	}
	
}
