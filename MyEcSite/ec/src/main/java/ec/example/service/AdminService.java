package ec.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ec.example.dao.AdminDao;
import ec.example.entity.AdminEntity;


@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	
	//ログイン処理
	public AdminEntity findByAdminNameAndPassword(String adminName, String password) {
		List<AdminEntity> adminList = adminDao.findByAdminNameAndPassword(adminName, password);
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
