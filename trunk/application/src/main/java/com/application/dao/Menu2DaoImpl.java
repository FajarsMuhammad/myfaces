package com.application.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.application.model.Menu2;

public class Menu2DaoImpl extends HibernateDaoSupport implements Menu2Dao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu2> searchMenu() {
		return getHibernateTemplate().find("from Menu2");
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu2> getMenuByParent(String parent){
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu2.class);
		criteria.add(Restrictions.eq("parentCode", parent));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
