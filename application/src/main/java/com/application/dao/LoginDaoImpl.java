/*package com.application.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.application.model.User;

public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao{

	@SuppressWarnings("unchecked")
	@Override
	public User loadUserByUsername(String userName){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (User) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}

}
*/