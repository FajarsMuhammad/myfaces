package com.application.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;

import com.application.model.User;

public class UserDaoImpl extends BasisDaoImpl<User, Long> implements UserDao {
	
	@SuppressWarnings("unchecked")
	public User getUserByLoginname(final String userName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (User) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByLoginnamePassword(final String userName, final String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));
		return (User) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers(final String userName){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
