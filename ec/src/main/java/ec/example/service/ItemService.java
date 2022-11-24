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

	public List<ItemEntity> returnSerach(int categoryId,String itemName){
		if(categoryId == 0 && itemName.equals("")) {
			return itemDao.findAll();
		}
		if(categoryId == 0) {
			return itemDao.findByItemNameLike("%"+itemName+"%");
		}

		if(itemName.equals("")) {
			return itemDao.findByCategoryId(categoryId);
		}else {
			return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
		}
	}
	
	//カテゴリーを探す
	public List<ItemEntity> selectByCategoryId(int categoryId){
		return itemDao.findByCategoryId(categoryId);
	}
	//名前を探す
	public List<ItemEntity> selectByItemName(String itemName){
		return itemDao.findByItemNameLike("%"+itemName+"%");
	}
	//カテゴリーと名前両方を探す
	public List<ItemEntity> selectByItemNameAndCategoryId(int categoryId,String itemName){
		return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
	}
	
	public ItemEntity selectByItemId(Long itemId){
		return itemDao.findByItemId(itemId);
	}
	
	//商品一覧を取得
	public List<ItemEntity> findAllItem(){
		return itemDao.findAll();
	}
	
	//商品を登録
	public void insert(String itemName,Integer categoryId,String image,Integer price,Integer stock,String detail,Integer active,Long adminId) {
		itemDao.save(new ItemEntity(itemName,categoryId,image,price,stock,detail,active,adminId));
	}
	//商品を登録
		public void insert(ItemEntity itemEntity) {
			itemDao.save(itemEntity);
		}
		
	//商品を編集
	public void update(Long itemId,String itemName,Integer categoryId,String image,Integer price,Integer stock,String detail,Integer active,Long adminId) {
		itemDao.save(new ItemEntity(itemId,itemName,categoryId,image,price,stock,detail,active,adminId));
	}

	
}
