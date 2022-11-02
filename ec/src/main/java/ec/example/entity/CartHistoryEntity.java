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
@Table(name="carthistory")
public class CartHistoryEntity {
	public CartHistoryEntity(Long itemId, int num, Long cartId) {
		this.itemId = itemId;
		this.num = num;
		this.cartId = cartId;
	}

	@Id
	@Column(name="carthistory_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartHistoryId;
	
	@Column(name="item_id")
	private Long itemId;
	
	@Column(name="num")
	private int num;
	
	@Column(name="cart_id")
	private Long cartId;
}
