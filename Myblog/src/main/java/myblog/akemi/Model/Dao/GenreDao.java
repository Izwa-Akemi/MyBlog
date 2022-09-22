package myblog.akemi.Model.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import myblog.akemi.Model.Entity.AdminEntity;
import myblog.akemi.Model.Entity.GenreEntity;

@Repository
public interface GenreDao extends JpaRepository<GenreEntity, Long> {
	GenreEntity save(GenreEntity genreentity);
	@Query("SELECT a.admin_id,g.genre_name,g.genre_id FROM AdminiEntity AS a INNER JOIN GenreEntity g ON a.admin_id = g.admin_id WHERE a.admin_id = :adminId ")
	List<GenreEntity> find(@Param("adminId") Integer adminId);
}
