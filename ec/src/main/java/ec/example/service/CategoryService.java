package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.CategoryDao;
import ec.example.entity.CartEntity;
import ec.example.entity.CategoryEntity;
import ec.example.entity.UserEntity;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	//内容を保存
	public void insert(String categoryName,int active) {
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		categoryDao.save(new CategoryEntity(categoryName,active));
	}
	
	//内容を更新
	public void update(Long categoryId,String categoryName,int active) {
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		categoryDao.save(new CategoryEntity(categoryId,categoryName,active));
	}
	//コントローラークラスで受け取ったcategoryIdを基にデータを取得する
	public CategoryEntity selectByCategoryId(Long categoryId) {
		//コントローラークラスで受け取ったパラメータをdaoのfindByCategoryIdメソッドに値を渡して内容を取得する
		return categoryDao.findByCategoryId(categoryId);
	}
	
	//更新
	//コントローラークラスからcartIdとactiveを取得する
		public void update(Long categoryId,int active) {
			//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
			categoryDao.save(new CategoryEntity(categoryId,active));
		}
	
	//Daoクラスから一覧の情報をを取得する。
	public List<CategoryEntity> selecFindAll(){
		return categoryDao.findAll();
	}
	
}
