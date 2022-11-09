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
@Table(name="itemhistory")
public class ItemHistoryEntity {
	@Id
	@Column(name="itemhistory_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemhistoryId;
	
	@Column(name="item_id")
	private Long itemId;
	
	@Column(name="num")
	private int num;
	
	@Column(name="order_id")
	private Long ordeId;
}
