package ec.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.example.dao.PrefDao;
import ec.example.entity.PrefEntity;

@Service
public class PrefService {
	@Autowired
	private PrefDao prefDao;
	
	//一覧取得
    public List<PrefEntity> findAll() {
        return prefDao.findAll();
    }
}
