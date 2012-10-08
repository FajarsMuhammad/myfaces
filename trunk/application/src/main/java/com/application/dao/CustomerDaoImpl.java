package com.application.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;

import com.application.model.Customer;

public class CustomerDaoImpl extends BasisDaoImpl<Customer, Long> implements CustomerDao {
	
	Logger log = Logger.getLogger(CustomerDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList){
		int columnSize = 0;
		int valueSize = 0;

		if (columnList != null)
			columnSize = columnList.size();
		if (valueList != null)
			valueSize = valueList.size();
		
		if (columnSize != valueSize)
			throw new HibernateException(
					"Number of 'column' must be equals with number of 'value'");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		for(int i=0; i<columnSize; i++){
			criteria.add(Restrictions.ilike((String)columnList.get(i), (String)valueList.get(i), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("code"));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList, int startingAt, int maxPerPage){
		int columnSize = 0;
		int valueSize = 0;

		if (columnList != null)
			columnSize = columnList.size();
		if (valueList != null)
			valueSize = valueList.size();
		
		if (columnSize != valueSize)
			throw new HibernateException(
					"Number of 'column' must be equals with number of 'value'");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		for(int i=0; i<columnSize; i++){
			criteria.add(Restrictions.ilike((String)columnList.get(i), (String)valueList.get(i), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("code"));
		
		return getHibernateTemplate().findByCriteria(criteria, startingAt, maxPerPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomer(int startingAt, int maxPerPage){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.addOrder(Order.desc("code"));
		
		return getHibernateTemplate().findByCriteria(criteria, startingAt, maxPerPage);
	}

	public Customer searchCustomerById(long id) {
		return getHibernateTemplate().get(Customer.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Customer> generateCode(String code) {
		String query = "FROM Customer WHERE code LIKE '" + code
				+ "%' ORDER BY code DESC";
		Query result = getSession().createQuery(query);
		return result.list();
	}
	
	public int getCountAllCustomer() {
		return DataAccessUtils.intResult(getHibernateTemplate().find("select count(*) from Customer"));
	}
}