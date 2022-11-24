package ec.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemAndBookMarkEntity {
	@Id
	@Column(name="item_id")
	private Long itemId;

	@Column(name="user_id")
	private Long userId;

	@Column(name="bookmark_id")
	private Long bookmarkId;

	@Column(name="item_name")
	private String itemName;
	
	@Column(name="detail")
	private String detail;

	@Column(name="image")
	private String image;

	@Column(name="price")
	private Integer price;
	
	@Column(name="stock")
	private Integer stock;

}
