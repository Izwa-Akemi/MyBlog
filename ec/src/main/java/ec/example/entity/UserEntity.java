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
@Table(name="user")
public class UserEntity {
	
	
	public UserEntity(String userName,String userEmail,String password, int prefId,String zipCode,String address) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.prefId = prefId;
		this.zipCode = zipCode;
		this.address = address;
	}

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NonNull
	@Column(name="user_name")
	private String userName;

	@NonNull
	@Column(name="user_email")
	private String userEmail;

	@NonNull
	@Column(name="password")
	private String password;
	
	@Column(name="pref_id")
	private int prefId ;
	
	@NonNull
	@Column(name="zip_code")
	private String zipCode;
	
	@NonNull
	@Column(name="address")
	private String address;
	
	@Column(name="active")
	private int active;
	
	@Column(name="register_date")
	private String registerDate;
	
	
}
