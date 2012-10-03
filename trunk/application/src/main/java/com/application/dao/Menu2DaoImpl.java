package com.application.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
		criteria.addOrder(Order.asc("sequence"));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu2> getMenuByMenuCode(String menuCode){
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu2.class);
		criteria.add(Restrictions.eq("menuCode", menuCode));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu2> getMenuByUser(String user){
		Query query = getSession().createQuery(
				"from Menu2 m where m.id in (select distinct m.id " +
				"from UserRole ur, RoleMenu rm, Menu2 m " +
				"where ur.user.userName = :userName and ur.role.id = rm.role.id and rm.menu.id = m.id) order by m.sequence");
		query.setString("userName", user);
		//query.setString("parent", parentCode);
		
		return query.list();
	}
	
	

}
