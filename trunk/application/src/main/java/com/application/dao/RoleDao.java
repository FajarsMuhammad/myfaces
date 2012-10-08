package com.application.dao;

import java.util.List;

import com.application.model.Role;

public interface RoleDao extends BasisDao<Role, Long> {

	public List<Role> searchRole(List<Object> columnList, List<Object> valueList);

	public Role getById(long id);


}