package com.application.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;

import com.application.model.RoleMenu;

public class RoleMenuDaoImpl extends BasisDaoImpl<RoleMenu, Long> implements
		RoleMenuDao {

	Logger log = Logger.getLogger(RoleMenuDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<RoleMenu> searchRoleMenu() {

		DetachedCriteria criteria = DetachedCriteria.forClass(RoleMenu.class);

		return getHibernateTemplate().findByCriteria(criteria);
	}

	public RoleMenu getById(long id) {
		return getHibernateTemplate().get(RoleMenu.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<RoleMenu> searchRoleMenuByRole(Long id) {

		DetachedCriteria criteria = DetachedCriteria.forClass(RoleMenu.class);
		criteria.add(Restrictions.eq("role.id", id));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public RoleMenu searchRoleMenuByRoleAndMenu(Long roleId, Long menuId) {

		DetachedCriteria criteria = DetachedCriteria.forClass(RoleMenu.class);
		criteria.add(Restrictions.eq("role.id", roleId));
		criteria.add(Restrictions.eq("menu.id", menuId));
		return (RoleMenu)DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}

}