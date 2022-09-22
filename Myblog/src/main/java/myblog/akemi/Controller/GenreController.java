package myblog.akemi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import myblog.akemi.Model.Entity.GenreEntity;
import myblog.akemi.Model.Service.GenreService;

@Controller
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	//一覧を表示
	@GetMapping("/genreAll/{adminId}")
	public String getGenreAll(@PathVariable Integer adminId,Model model) {
		List<GenreEntity>genrelist = genreService.findAll(adminId);
		model.addAttribute("genrelist",genrelist);
		return "genre_all_view.html";
	}

}
