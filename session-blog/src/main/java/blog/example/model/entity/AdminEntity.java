package blog.example.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="admin")
public class AdminEntity {
	@Id
	@Column(name="admin_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;

	@NonNull
	@Column(name="admin_name")
	private String userName;
	
	@NonNull
	@Column(name="admin_email")
	private String adminEmail;

	@NonNull
	@Column(name="password")
	private String password;
}
