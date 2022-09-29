package blog.example.Model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.Model.Dao.CategoryDao;
import blog.example.Model.Entity.AdminEntity;
import blog.example.Model.Entity.CategoryEntity;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	//一覧表示
	//一覧取得
		public List<CategoryEntity> findByAdminId(Long adminId) {
//			System.out.println(i);
			return categoryDao.findByAdminId(adminId);
		}

		//内容を保存
		public void insert(String categoryName,Long adminId) {
			categoryDao.save(new CategoryEntity(categoryName,adminId));
		}
}
