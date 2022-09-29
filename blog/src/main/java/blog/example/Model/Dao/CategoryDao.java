package blog.example.Model.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import blog.example.Model.Entity.CategoryEntity;
@Repository

public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity save(CategoryEntity categoryEntity);
	@Query(value="SELECT c.category_id,c.category_name,c.admin_id FROM admins AS a INNER JOIN categories c ON a.admin_id = c.admin_id "
			+ "WHERE a.admin_id = ?1",nativeQuery = true)
	List<CategoryEntity> findByAdminId(Long adminId);
}
