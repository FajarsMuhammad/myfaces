package com.application.service;

import java.util.List;

import com.application.model.Customer;

public interface CustomerService {

	public void save(Customer customer);
	
	public void update(Customer customer);

	public void delete(Customer customer);

	public List<Customer> findAllCustomer();

	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList);

	public Customer searchCustomerById(long id);
		
	public List<Customer> generateCode(String code);
}