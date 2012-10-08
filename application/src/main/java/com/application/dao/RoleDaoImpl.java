package com.application.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.application.model.Role;

public class RoleDaoImpl extends BasisDaoImpl<Role, Long> implements RoleDao {
	
	Logger log = Logger.getLogger(RoleDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Role> searchRole(List<Object> columnList, List<Object> valueList){
		int columnSize = 0;
		int valueSize = 0;

		if (columnList != null)
			columnSize = columnList.size();
		if (valueList != null)
			valueSize = valueList.size();
		
		if (columnSize != valueSize)
			throw new HibernateException(
					"Number of 'column' must be equals with number of 'value'");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		for(int i=0; i<columnSize; i++){
			criteria.add(Restrictions.ilike((String)columnList.get(i), (String)valueList.get(i), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("roleShortDescription"));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public Role getById(long id) {
		return getHibernateTemplate().get(Role.class, id);
	}

	

}