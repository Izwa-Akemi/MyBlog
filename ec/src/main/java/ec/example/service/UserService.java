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
			//コントローラークラスからuserNameとpasswordと受け取って結果を受け取る
			List<UserEntity> userList = userDao.findByUserNameAndPassword(userName, password);
			//もしuserListが空だった場合には、nullを返す処理
			if(userList.isEmpty()){
		        return null;
		    }else{
		        return userList.get(0);
		    }

		}
		
		
		//内容を保存
		public void insert(String userName,String userEmail,String password,int prefId,String zipCode,String address) {
			//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
			userDao.save(new UserEntity(userName,userEmail,password,prefId,zipCode,address));
		}
}
