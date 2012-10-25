package com.application.service;

import java.io.Serializable;
import java.util.List;

import com.application.dao.RoleMenuDao;
import com.application.dao.RoleMenuDao;
import com.application.model.Role;
import com.application.model.RoleMenu;

public class RoleMenuServiceImpl implements RoleMenuService, Serializable {
	
	private static final long serialVersionUID = -2008597716411567038L;
	private RoleMenuDao roleMenuDao;


	public RoleMenuDao getRoleMenuDao() {
		return roleMenuDao;
	}

	public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}


	public List<RoleMenu> searchRoleMenu(){
		return roleMenuDao.searchRoleMenu();
	}
	
	public RoleMenu getById(long id){
		return roleMenuDao.getById(id);
	}
	
	
	public void update(RoleMenu roleMenu) {
		roleMenuDao.update(roleMenu);
	}

	public void delete(RoleMenu roleMenu) {
		roleMenuDao.delete(roleMenu);
	}
	
	public void save(RoleMenu customer) {
		roleMenuDao.save(customer);
	}

	public List<RoleMenu> findAll() {
		return roleMenuDao.findAll();
	}
	
	public List<RoleMenu> searchRoleMenuByRole(Long id){
		return roleMenuDao.searchRoleMenuByRole(id);
	}
	
	public RoleMenu searchRoleMenuByRoleAndMenu(Long roleId, Long menuId){
		return roleMenuDao.searchRoleMenuByRoleAndMenu(roleId, menuId);
	}

}
