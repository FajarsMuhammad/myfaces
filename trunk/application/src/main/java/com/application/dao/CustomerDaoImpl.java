package com.application.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.application.model.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	public void addCustomer(Customer customer) {
		customer.setCreatedDate(new Date());
		getHibernateTemplate().save(customer);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomer() {
		return getHibernateTemplate().find("from Customer");
	}
	
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

	public Customer searchCustomerById(long id) {
		return getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public void editCustomer(Customer customer) {
		 getHibernateTemplate().saveOrUpdate(customer);		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		getHibernateTemplate().delete(customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> generateCode(String code) {
		String query = "FROM Customer WHERE code LIKE '" + code
				+ "%' ORDER BY code DESC";
		Query result = getSession().createQuery(query);
		return result.list();
	}
}