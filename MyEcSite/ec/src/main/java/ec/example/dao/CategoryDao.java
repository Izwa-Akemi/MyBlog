package ec.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.example.entity.CategoryEntity;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {

}
