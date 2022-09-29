package blog.example.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.example.Model.Entity.AdminEntity;

@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
    AdminEntity save(AdminEntity adminEntity);
}