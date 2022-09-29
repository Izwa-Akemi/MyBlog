package blog.example.Model.Entity;

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
@Table(name="categories")
public class CategoryEntity {
	
	
	public CategoryEntity(String categoryName, Long adminId) {
		this.categoryName = categoryName;
		this.adminId = adminId;
	}

	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	@NonNull
    @Column(name="category_name")
    private String categoryName;
	
	
	@Column(name="admin_id")
    private Long adminId;
	
	
	

}
