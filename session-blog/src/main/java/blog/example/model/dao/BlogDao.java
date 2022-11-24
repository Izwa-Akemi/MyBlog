package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.example.model.entity.BlogEntity;



public interface BlogDao extends JpaRepository<BlogEntity, Long> { 
	BlogEntity save(BlogEntity blogEntity);
	BlogEntity findByBlogId(Long blogId);
	List<BlogEntity>findAll();
}
