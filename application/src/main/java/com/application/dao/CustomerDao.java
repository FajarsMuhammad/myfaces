package com.application.dao;

import java.util.List;

import com.application.model.Customer;

public interface CustomerDao extends BasisDao<Customer, Long> {

	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList);
	
	public List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList, int startingAt, int maxPerPage);
	
	public List<Customer> searchCustomer(int startingAt, int maxPerPage);

	public Customer searchCustomerById(long id);
	
	public List<Customer> generateCode(String code);
	
	public int getCountAllCustomer();

}