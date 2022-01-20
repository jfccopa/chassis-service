package com.threetrack.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ad_user")
public class User extends GenericEntity {

	@Id
	@Column(name = "i_user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "v_name", length = 32)
	private String name;

	@Column(name = "v_password", length = 64)
	private String password;

	@Column(name = "c_state")
	private char state;

	@Column(name = "i_person_id")
	private Integer personId;

	@Column(name = "i_organization_id")
	private Integer organizationId;

	public User() {}
}
