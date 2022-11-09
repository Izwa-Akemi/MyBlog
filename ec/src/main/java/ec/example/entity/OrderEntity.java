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
@Table(name="orders")
public class OrderEntity {
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="order_date")
	private String orderDate;
}
