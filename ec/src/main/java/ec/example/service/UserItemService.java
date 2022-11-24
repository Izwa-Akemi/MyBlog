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
	public List<ItemAndBookMarkEntity> returnSerach(int categoryId,String itemName){
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
	public List<ItemAndBookMarkEntity> selectByCategoryId(int categoryId){
		return itemDao.findByCategoryId(categoryId);
	}
	//名前を探す
	public List<ItemAndBookMarkEntity> selectByItemName(String itemName){
		return itemDao.findByItemNameLike("%"+itemName+"%");
	}
	//カテゴリーと名前両方を探す
	public List<ItemAndBookMarkEntity> selectByItemNameAndCategoryId(int categoryId,String itemName){
		return itemDao.findByCategoryIdAndItemNameLike(categoryId,"%"+itemName+"%");
	}
	
	public ItemAndBookMarkEntity selectByItemId(Long itemId){
		return itemDao.findByItemId(itemId);
	}
	
	//商品一覧を取得
	public List<ItemAndBookMarkEntity> findAllItem(){
		return itemDao.findAll();
	}
	
}
