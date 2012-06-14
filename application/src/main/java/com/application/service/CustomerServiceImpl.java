package com.application.service;
 
import java.util.List;

import com.application.dao.CustomerDao;
import com.application.model.Customer;
 
public class CustomerServiceImpl implements CustomerService{
 
	CustomerDao customerDao;
 
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
 
	public void addCustomer(Customer customer){ 
		customerDao.addCustomer(customer); 
	}
 
	public List<Customer> findAllCustomer(){ 
		return customerDao.findAllCustomer();
	}

	@Override
	public Customer searchCustomerById(long id){
		return customerDao.searchCustomerById(id);
	}

	@Override
	public void editCustomer(Customer customer) {
		customerDao.editCustomer(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerDao.deleteCustomer(customer);
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
}