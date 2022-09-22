package myblog.akemi.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myblog.akemi.Model.Entity.AdminEntity;



@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
    AdminEntity save(AdminEntity adminentity);
}