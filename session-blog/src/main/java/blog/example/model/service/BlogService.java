package blog.example.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.BlogDao;
import blog.example.model.entity.BlogEntity;


@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;

	//内容を保存
	public void insert(String blogTitle,String fileName,String message) {
		blogDao.save(new BlogEntity(blogTitle,fileName,message));
	}
	
	//ブログ詳細
	public BlogEntity selectByBlogId(Long blogId){
		return blogDao.findByBlogId(blogId);
	}
	//内容を更新
	public void update(Long blogId,String blogTitle,String fileName,String message) {
		blogDao.save(new BlogEntity(blogId,blogTitle,fileName,message));
	}

	//ブログ一覧
	public List<BlogEntity> selectByAll(){
		return blogDao.findAll();
	}

	

}
