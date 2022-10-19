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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="item")
public class ItemEntity {
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemId;
	
	@NonNull
	@Column(name="item_name")
	private String itemName;
	

	@Column(name="category_id")
	private Integer categoryId;
	
	@NonNull
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private Integer price;
	
	
	@Column(name="stock")
	private Integer stock;
	
	@NonNull
	@Column(name="detail")
	private String detail;
	
	@Column(name="active")
	private int active;
	
	
	@Column(name="admin_id")
	private Integer adminId;
}
