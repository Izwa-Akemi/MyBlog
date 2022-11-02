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
@Table(name="admin")
public class AdminEntity {
	@Id
	@Column(name="admin_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;
	
	@NonNull
	@Column(name="admin_name")
	private String adminName;

	@NonNull
	@Column(name="admin_email")
	private String adminEmail;

	@NonNull
	@Column(name="password")
	private String password;
	
	@Column(name="active")
	private int active;
	
}
