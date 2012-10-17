package com.application.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;

import com.application.model.RoleMenu;

public class RoleMenuDaoImpl extends BasisDaoImpl<RoleMenu, Long> implements RoleMenuDao {
	
	Logger log = Logger.getLogger(RoleMenuDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<RoleMenu> searchRoleMenu(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(RoleMenu.class);
		
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public RoleMenu getById(long id) {
		return getHibernateTemplate().get(RoleMenu.class, id);
	}

	

}