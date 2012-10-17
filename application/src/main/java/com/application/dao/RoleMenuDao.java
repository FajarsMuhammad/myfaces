package com.application.dao;

import java.util.List;

import com.application.model.RoleMenu;

public interface RoleMenuDao extends BasisDao<RoleMenu, Long> {

	public List<RoleMenu> searchRoleMenu();

	public RoleMenu getById(long id);


}