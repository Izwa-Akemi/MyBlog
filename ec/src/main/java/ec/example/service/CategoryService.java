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
		categoryDao.save(new CategoryEntity(categoryName,active));
	}
	
	//内容を保存
	public void update(Long categoryId,String categoryName,int active) {
		categoryDao.save(new CategoryEntity(categoryId,categoryName,active));
	}
	
	public CategoryEntity selectByCategoryId(Long categoryId) {
		return categoryDao.findByCategoryId(categoryId);
	}
	
	//削除
		public void update(Long categoryId,int active) {
			categoryDao.save(new CategoryEntity(categoryId,active));
		}
	

	public List<CategoryEntity> selecFindAll(){
		return categoryDao.findAll();
	}
	
}
