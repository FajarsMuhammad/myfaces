package com.application.dao;

import java.util.List;

import com.application.model.Customer;

public interface CustomerDao {

	void addCustomer(Customer customer);

	List<Customer> findAllCustomer();
	
	List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList);

	void editCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	Customer searchCustomerById(long id);
	
	List<Customer> generateCode(String code);

}