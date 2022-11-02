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
@Table(name="pref")
public class PrefEntity {
	@Id
	@Column(name="pref_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long prefId;

	@NonNull
	@Column(name="pref_name")
	private String prefName;
}
