package com.application.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract  class BasisDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BasisDao<T, ID>{

	public Class<T> domainClass;
	
	public BasisDaoImpl(){
		 this.domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
		
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getHibernateTemplate().find("from " + domainClass.getName());
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(domainClass);
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	@Override
	public int count() {
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock)
	{
		T entity;
		if (lock)
			entity = (T) getSession().load(domainClass, id, LockMode.UPGRADE);
		else
			entity = (T) getSession().load(domainClass, id);
		
		return entity;
	}
	

}
