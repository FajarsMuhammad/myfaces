package com.application.service;

import java.io.Serializable;
import java.util.List;

import com.application.dao.RoleDao;
import com.application.model.Role;

public class RoleServiceImpl implements RoleService, Serializable {
	
	private static final long serialVersionUID = -2008597716411567038L;
	private RoleDao roleDao;


	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	public List<Role> searchRole(List<Object> columnList, List<Object> valueList){
		return roleDao.searchRole(columnList, valueList);
	}
	
	public List<Role> searchRole(){
		return roleDao.searchRole();
	}
	
	public Role getById(long id){
		return roleDao.getById(id);
	}
	
	
	public void update(Role role) {
		roleDao.update(role);
	}

	public void delete(Role role) {
		roleDao.delete(role);
	}
	
	public void save(Role customer) {
		roleDao.save(customer);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
