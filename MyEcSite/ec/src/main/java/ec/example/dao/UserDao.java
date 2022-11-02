package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.example.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity save(UserEntity userEntity);
	List<UserEntity> findByUserNameAndPassword(String userName, String password);
}