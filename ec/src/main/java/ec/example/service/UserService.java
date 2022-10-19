package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ec.example.dao.UserDao;
import ec.example.entity.UserEntity;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	//ログイン処理
		public UserEntity findByUserNameAndPassword(String userName, String password) {
			List<UserEntity> userList = userDao.findByUserNameAndPassword(userName, password);
		    if(userList.isEmpty()){
		        return null;
		    }else{
		        return userList.get(0);
		    }

		}
		
		
		//内容を保存
		public void insert(String userName,String userEmail,String password,int prefId,String zipCode,String address) {
			userDao.save(new UserEntity(userName,userEmail,password,prefId,zipCode,address));
		}
}
