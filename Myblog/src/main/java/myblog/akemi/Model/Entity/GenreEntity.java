package myblog.akemi.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="genres")

public class GenreEntity {
	@Id
	@Column(name="genre_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genreId;

	@NonNull
	@Column(name="genre_name")
	private String genreName;
	
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private GenreEntity genreEntity;

}
