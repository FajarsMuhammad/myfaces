package com.application.service;

import java.util.List;

import com.application.dao.CustomerDao;
import com.application.model.Customer;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void save(Customer customer) {
		customerDao.save(customer);
	}

	public List<Customer> findAllCustomer() {
		return customerDao.findAll();
	}

	@Override
	public Customer searchCustomerById(long id) {
		return customerDao.searchCustomerById(id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	
	@Override
	public void deleteAll(List<Customer> customers) {
		customerDao.deleteAll(customers);
	}

	@Override
	public List<Customer> searchCustomer(List<Object> columnList,
			List<Object> valueList) {
		return customerDao.searchCustomer(columnList, valueList);
	}

	@Override
	public List<Customer> generateCode(String code) {
		return customerDao.generateCode(code);
	}
	
	public List<Customer> searchCustomer(int startingAt, int maxPerPage){
		return customerDao.searchCustomer(startingAt, maxPerPage);
	}
	
	public int getCountAllCustomer(){
		return customerDao.getCountAllCustomer();
	}
	
	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList, int startingAt, int maxPerPage){
		return customerDao.searchCustomer(columnList, valueList, startingAt, maxPerPage);
	}
}