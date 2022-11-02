package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.example.entity.AdminEntity;


@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
	AdminEntity save(AdminEntity adminEntity);
	List<AdminEntity> findByAdminNameAndPassword(String adminName, String password);
}