package ec.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartHistoryAndItemEntity {
	@Id
	private Long cartHistoryId;
	private Long cartId;
	private Long itemId;
	private String itemName;
	private String image;
	private Integer price;
	private Integer stock;
	private String detail;
}
