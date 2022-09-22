package myblog.akemi.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import myblog.akemi.Model.Dao.GenreDao;

import myblog.akemi.Model.Entity.GenreEntity;

@Service
public class GenreService {
	@Autowired
	private GenreDao genreDao;

	//一覧取得
	public List<GenreEntity> findAll(Integer adminId) {
		return genreDao.find(adminId);
	}

	//内容を保存
//	public void insert(String genreName) {
//		genreDao.save(new GenreEntity(genreName));
//	}

	//内容をupdate
	/*public void update(Long genreId,String genreName) {
		genreDao.save(new GenreEntity(genreId,genreName));
	}*/

	//削除
//	public void delete(Long genreId) {
//		genreDao.deleteById(genreId);
//	}

	//idを見つける
//	public Optional<GenreEntity> selectById(Long genreId) {
//		return genreDao.findById(genreId);
//	}

}