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
@Table(name="cart")
public class CartEntity {
	public CartEntity(Long userId, String cartDate) {
		this.userId = userId;
		this.cartDate = cartDate;
	}

	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="cart_date")
	private String cartDate;
}
