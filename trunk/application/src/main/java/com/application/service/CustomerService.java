package com.application.service;

import java.util.List;

import com.application.model.Customer;

public interface CustomerService {

	void addCustomer(Customer customer);

	List<Customer> findAllCustomer();

	List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList);

	Customer searchCustomerById(long id);

	void editCustomer(Customer customer);

	void deleteCustomer(Customer customer);
	
	List<Customer> generateCode(String code);
}