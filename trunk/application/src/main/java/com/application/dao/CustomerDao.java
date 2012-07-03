package com.application.dao;

import java.util.List;

import com.application.model.Customer;

public interface CustomerDao extends BasisDao<Customer, Long> {

	List<Customer> searchCustomer(List<Object> columnList, List<Object> valueList);

	Customer searchCustomerById(long id);
	
	List<Customer> generateCode(String code);

}