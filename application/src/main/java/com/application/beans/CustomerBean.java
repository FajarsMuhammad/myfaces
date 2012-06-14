package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import com.application.model.Customer;
import com.application.service.CustomerService;
import com.application.utility.GenerateCode;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

public class CustomerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051005476848367530L;

	// via Spring
	private CustomerService customerService;
	private GenerateCode generateCode;
	
	public String custId;
	public String code;
	public String name;
	public String address;
	public String gender;
	public String grade;
	public String searchColumn;
	public String searchValue;
	public boolean termOfPayment;

	private String customerInputRedir = "/pages/customer/customerInput.xhtml";

	public String getCustomerInputRedir() {
		return customerInputRedir;
	}

	public void setCustomerInputRedir(String customerInputRedir) {
		this.customerInputRedir = customerInputRedir;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isTermOfPayment() {
		return termOfPayment;
	}

	public void setTermOfPayment(boolean termOfPayment) {
		this.termOfPayment = termOfPayment;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setGenerateCode(GenerateCode generateCode) {
		this.generateCode = generateCode;
	}

	/**
	 * get all customer data from database
	 */
	public List<Customer> getCustomerList() {
		List<Object> columnList = null;
		List<Object> valueList = null;
		List<Customer> customers = new ArrayList<Customer>();

		if (searchValue != null && !searchValue.trim().equals("")) {
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue);
		}

		customers = customerService.searchCustomer(columnList, valueList);
		if (customers.isEmpty()) {
			customers = new ArrayList<Customer>();
		}
		return customers;
	}

	/**
	 * Methode button search
	 */
	public void search() {
		getCustomerList();
	}

	// Column List
	public List<LabelValueBean> getColumnList() {
		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
		columnList.add(new LabelValueBean(ResourceHelper.getResources("label.customerCode"), "code"));
		columnList.add(new LabelValueBean(ResourceHelper.getResources("label.customerName"), "name"));
		return columnList;
	}

	// Grade List
	public List<LabelValueBean> getGradeList() {
		List<LabelValueBean> gradeList = new ArrayList<LabelValueBean>();
		gradeList.add(new LabelValueBean(ResourceHelper.getResources("label.gradeA"), "A"));
		gradeList.add(new LabelValueBean(ResourceHelper.getResources("label.gradeB"), "B"));
		gradeList.add(new LabelValueBean(ResourceHelper.getResources("label.gradeC"), "C"));
		gradeList.add(new LabelValueBean(ResourceHelper.getResources("label.gradeD"), "D"));
		return gradeList;
	}


	// redirect to form
	public void initialAdd() {
		setCode(generateCode.generateCustomerCode());
	}

	public void gotoPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nav = fc.getApplication().getNavigationHandler();
		nav.handleNavigation(fc, null,
				"/pages/customer/customerInput?faces-redirect=true");
		fc.renderResponse();
	}

	/**
	 * Tambah customer data into database untuk commandLink di halaman jsf
	 */
	public String addCustomer() {
		Customer customer = new Customer();
		customer.setCode(getCode());
		customer.setName(getName());
		customer.setAddress(getAddress());
		customer.setGrade(getGrade());
		// customer.setTermOfPayment(getTermOfPayment() == true ? 1 : 0);
		customerService.addCustomer(customer);
		clearForm();
		return null;
	}

	/**
	 * Tambah customer data into database untuk commandbutton di halaman jsf
	 */
	public void saveCustomer() {
		Customer customer = new Customer();
		customer.setCode(generateCode.generateCustomerCode());
		customer.setName(getName());
		customer.setAddress(getAddress());
		customer.setGrade(getGrade());
		customer.setTermOfPayment(isTermOfPayment() ? 1 : 0);
		customerService.addCustomer(customer);
		clearForm();
	}

	/**
	 * Method pada saat update screen
	 */
	public String initialUpdate() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("customerId");
		Customer customer = customerService.searchCustomerById(new Long(id));
		this.setCustId("" + customer.getCustomerId());
		this.setCode(customer.getCode());
		this.setName(customer.getName());
		this.setTermOfPayment(customer.getTermOfPayment() == 1 ? true : false);
		this.setGrade(customer.getGrade() != null ? customer.getGrade() : "");
		this.setAddress(customer.getAddress());
		return "/pages/customer/customerEdit.xhtml";
	}

	/**
	 * Edit customer data into database untuk commandLink di halaman jsf
	 */
	public String editCustomer() {
		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("custId");
		Customer customer = customerService.searchCustomerById(new Long(id));
		customer.setName(getName());
		customer.setAddress(getAddress());
		customerService.editCustomer(customer);
		clearForm();
		return "/pages/customer/customerList.xhtml";

	}

	/**
	 * Edit customer data ke database untuk commandButton di halaman jsf
	 */
	public void updateCustomer() {
		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("custId");
		Customer customer = customerService.searchCustomerById(new Long(id));
		System.out.println("CODE == "+getCode()+" "+this.code+" "+code +" "+this.getCode());
		customer.setCode(customer.getCode());
		customer.setName(this.getName());
		customer.setAddress(this.getAddress());
		customer.setGrade(this.getGrade());
		customer.setTermOfPayment(this.isTermOfPayment() ? 1 : 0);
		customerService.editCustomer(customer);
		clearForm();
	}

	/**
	 * Delete
	 */
	public String deleteCustomer() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("customerId");
		Customer customer = customerService.searchCustomerById(new Long(id));
		customerService.deleteCustomer(customer);
		return null;
	}

	/**
	 * clear form values
	 */
	private void clearForm() {
		setName("");
		setAddress("");
	}

}
