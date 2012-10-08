package com.application.model;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = -179692308943087373L;

	private long id;

	private String roleShortDescription;

	private String roleLongDescription;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
