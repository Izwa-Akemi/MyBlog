package ec.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.example.entity.PrefEntity;


@Repository
public interface PrefDao extends JpaRepository<PrefEntity, Long> {
	
}
