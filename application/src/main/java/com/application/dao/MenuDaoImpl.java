package com.application.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.application.model.Menu;

public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> searchMenu() {
		return getHibernateTemplate().find("from Menu");
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuByParent(String parent){
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		criteria.add(Restrictions.eq("parent", parent));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
