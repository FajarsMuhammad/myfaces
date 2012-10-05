package com.application.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;

import com.application.model.Menu;

public class MenuDaoImpl  extends BasisDaoImpl<Menu, Long> implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> searchMenu() {
		return getHibernateTemplate().find("from Menu");
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> searchMenu(List<Object> columnList, List<Object> valueList){
		int columnSize = 0;
		int valueSize = 0;

		if (columnList != null)
			columnSize = columnList.size();
		if (valueList != null)
			valueSize = valueList.size();
		
		if (columnSize != valueSize)
			throw new HibernateException(
					"Number of 'column' must be equals with number of 'value'");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		for(int i=0; i<columnSize; i++){
			criteria.add(Restrictions.ilike((String)columnList.get(i), (String)valueList.get(i), MatchMode.ANYWHERE));
		}
		//criteria.addOrder(Order.asc("menuCode"));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuByParent(String parent){
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		criteria.add(Restrictions.eq("parentCode", parent));
		criteria.addOrder(Order.asc("sequence"));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuByMenuCode(String menuCode){
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		criteria.add(Restrictions.eq("menuCode", menuCode));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuByUser(String user){
		Query query = getSession().createQuery(
				"from Menu m where m.id in (select distinct m.id " +
				"from UserRole ur, RoleMenu rm, Menu m " +
				"where ur.user.userName = :userName and ur.role.id = rm.role.id and rm.menu.id = m.id) order by m.sequence");
		query.setString("userName", user);
		//query.setString("parent", parentCode);
		
		return query.list();
	}
	
	public Menu getById(long id) {
		return getHibernateTemplate().get(Menu.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Menu getByCode(String code) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		criteria.add(Restrictions.eq("menuCode", code));

		return (Menu) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}
	
	public void initialize(Menu menu) {
		super.initialize(menu);
	}


}
