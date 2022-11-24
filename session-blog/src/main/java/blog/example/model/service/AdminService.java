package blog.example.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.AdminDao;
import blog.example.model.entity.AdminEntity;



@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	
	//ログイン処理
	public AdminEntity selectByAdminEmailAndPassword(String adminEmail, String password) {
		List<AdminEntity> adminList = adminDao.findByAdminEmailAndPassword(adminEmail, password);
	    if(adminList.isEmpty()){
	        return null;
	    }else{
	        return adminList.get(0);
	    }

	}
	
		//内容を保存
		public void insert(String adminName,String adminEmail,String password) {
			adminDao.save(new AdminEntity(adminName,adminEmail,password));
		}
}
