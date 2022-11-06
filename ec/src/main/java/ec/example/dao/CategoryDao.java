package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ec.example.entity.CategoryEntity;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity save(CategoryEntity categoryEntity);
	List<CategoryEntity>findAll();
	CategoryEntity findByCategoryId(Long categoryId);
}
