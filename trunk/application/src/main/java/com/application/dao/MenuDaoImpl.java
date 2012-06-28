package com.application.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.application.model.Menu;

public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> searchMenu() {
		return getHibernateTemplate().find("from Menu");
	}

}
