package com.application.service;

import java.util.List;

import com.application.model.Role;

public interface RoleService {

	public List<Role> searchRole(List<Object> columnList, List<Object> valueList);
	
	public Role getById(long id);

	public void save(Role role);

	public List<Role> findAll();

	public void update(Role role);

	public void delete(Role role);
	
	

}