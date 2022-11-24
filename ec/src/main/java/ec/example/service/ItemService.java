package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.ItemAndBookMarkDao;
import ec.example.dao.ItemDao;
import ec.example.entity.ItemEntity;


@Service
public class ItemService {
	@Autowired
	ItemDao itemDao;
	
	//検索処理
	//コントローラークラスで受け取ったcategoryIdとitemNameを基にデータを取得する
	public List<ItemEntity> returnSerach(int categoryId,String itemName){
		
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
	public List<ItemEntity> selectByCategoryId(int categoryId){
		//daoクラスのfindByCategoryIdメソッドに値を渡して内容を取得する。
		return itemDao.findByCategoryId(categoryId);
	}
	//名前を探す
	//コントローラークラスで受け取ったitemNameを基にデータを取得する
	public List<ItemEntity> selectByItemName(String itemName){
		//daoクラスのfindByItemNameLikeメソッドに値を渡して内容を取得する。
		return itemDao.findByItemNameLike("%"+itemName+"%");
	}
	//カテゴリーと名前両方を探す
	//コントローラークラスで受け取ったcategoryIdとitemNameを基にデータを取得する
	public List<ItemEntity> selectByItemNameAndCategoryId(int categoryId,String itemName){
		//daoクラスのfindByCategoryIdAndItemNameLikeメソッドに値を渡して内容を取得する。
		return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
	}
	//コントローラークラスで受け取ったitemIdを基にデータを取得する
	public ItemEntity selectByItemId(Long itemId){
		//daoクラスのfindByItemIdメソッドに値を渡して内容を取得する。
		return itemDao.findByItemId(itemId);
	}
	
	//商品一覧を取得
	public List<ItemEntity> findAllItem(){
		//daoクラスのfindAllメソッドを使用してデータ一覧を取得する
		return itemDao.findAll();
	}
	
	//商品を登録
	//コントローラークラスで受け取ったパラメータを基にデータを保存する。
	public void insert(String itemName,Integer categoryId,String image,Integer price,Integer stock,String detail,Integer active,Long adminId) {
		//コントローラークラスで受け取った内容をdaoのsaveメソッドに渡して保存を行う。
		itemDao.save(new ItemEntity(itemName,categoryId,image,price,stock,detail,active,adminId));
	}
	//商品を登録
		public void insert(ItemEntity itemEntity) {
			//コントローラークラスで受け取った内容をdaoのsaveメソッドに渡して保存を行う。
			itemDao.save(itemEntity);
		}
		
	//商品を編集
	public void update(Long itemId,String itemName,Integer categoryId,String image,Integer price,Integer stock,String detail,Integer active,Long adminId) {
		//コントローラークラスで受け取った内容をdaoのsaveメソッドに渡して保存を行う。
		itemDao.save(new ItemEntity(itemId,itemName,categoryId,image,price,stock,detail,active,adminId));
	}

	
}
