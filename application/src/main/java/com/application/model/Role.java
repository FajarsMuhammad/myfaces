package com.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = -179692308943087373L;

	@Id
	@Column(name = "role_id")
	@SequenceGenerator(name = "my_seq", sequenceName = "role_id_seq")
	@GeneratedValue(generator = "my_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "role_shortdescription")
	private String roleShortDescription;

	@Column(name = "role_longdescription")
	private String roleLongDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleShortDescription() {
		return roleShortDescription;
	}

	public void setRoleShortDescription(String roleShortDescription) {
		this.roleShortDescription = roleShortDescription;
	}

	public String getRoleLongDescription() {
		return roleLongDescription;
	}

	public void setRoleLongDescription(String roleLongDescription) {
		this.roleLongDescription = roleLongDescription;
	}

}
