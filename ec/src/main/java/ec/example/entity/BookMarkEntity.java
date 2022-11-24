package ec.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookmark")
public class BookMarkEntity {

	@Id
	@Column(name="bookmark_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookmarkId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="item_id")
	private Long itemId;
	
	@Column(name="register_date")
	private String registerDate;
}
