/**
 * 
 */
package com.application.utility;

import java.util.List;

import com.application.model.Customer;
import com.application.service.CustomerService;

/**
 *
 * @author  fajar
 * @version 1.0
 * @created 25 Okt 2011
 *
 */
public class GenerateCode {

	private CustomerService customerService;
	
	public GenerateCode() {
	}
	
	
	public void setCustomerServiceTest(CustomerService customerService) {
		this.customerService = customerService;
	}


	public CustomerService getCustomerService() {
		return customerService;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public String generateCustomerCode(){
		String keygen = "C00001";
        String code = null;
        Integer countCode = new Integer(0);
        List<Customer> codeList = customerService.generateCode(keygen.substring(0,1));
        if(codeList.isEmpty()){
            code = keygen;
        }else{
            String temp = null;
            Customer customer = (Customer)codeList.get(0);
            temp = customer.getCode().substring(2);
            countCode = Integer.parseInt(temp) + 1;
            code = customer.getCode().substring(0,(customer.getCode().length() - String.valueOf(countCode).length())) + String.valueOf(countCode);
        }
        return code;
	}




}
