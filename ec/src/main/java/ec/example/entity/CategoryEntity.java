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
@Table(name="category")
public class CategoryEntity {
	public CategoryEntity(String categoryName, int active) {
		this.categoryName = categoryName;
		this.active = active;
	}

	public CategoryEntity(Long categoryId, int active) {
		this.categoryId = categoryId;
		this.active = active;
	}

	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	@NonNull
	@Column(name="category_name")
	private String categoryName;
	
	@Column(name="active")
	private int active;
	
}
