package com.application.service;

import java.util.List;

import com.application.model.RoleMenu;

public interface RoleMenuService {

	public List<RoleMenu> searchRoleMenu();
	
	public RoleMenu getById(long id);

	public void save(RoleMenu roleMenu);

	public List<RoleMenu> findAll();

	public void update(RoleMenu roleMenu);

	public void delete(RoleMenu roleMenu);
	
	

}