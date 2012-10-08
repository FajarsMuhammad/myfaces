package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.application.datamodel.CustomerDataModel;
import com.application.datamodel.CustomerLazyDataModel;
import com.application.model.Customer;
import com.application.service.CustomerService;
import com.application.utility.GenerateCode;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "customerBean")
@RequestScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 3051005476848367530L;
	private static final Logger log = Logger.getLogger(CustomerBean.class);

	private String custId;
	private String code;
	private String name;
	private String address;
	private String gender;
	private String grade;
	private String searchColumn;
	private String searchValue;
	private boolean termOfPayment;

	@ManagedProperty(value = "#{customerService}")
	private CustomerService customerService;

	@ManagedProperty(value = "#{generateCode}")
	private GenerateCode generateCode;

	private CustomerDataModel customerDataModel;
	private Customer[] selectedCustomers;

	@ManagedProperty(value = "#{lazyDataModel}")
	private LazyDataModel<Customer> lazyDataModel;

	private List<Customer> filteredCustomers;

	 public void setLazyDataModel(LazyDataModel<Customer> lazyDataModel) {
	 this.lazyDataModel = lazyDataModel;
	 }
	
	 public LazyDataModel<Customer> getLazyDataModel() {
	 if (lazyDataModel == null) {
	 lazyDataModel = new CustomerLazyDataModel();
	 }
	 return lazyDataModel;
	 }

	// public LazyDataModel<Customer> getLazyDataModel() {
	// if (lazyDataModel == null) {
	// lazyDataModel = new LazyDataModel<Customer>() {
	// List<Customer> customers;
	// @Override
	// public List<Customer> load(int first, int pageSize,
	// String sortField, SortOrder sortOrder,
	// Map<String, String> filters) {
	// log.info("first=" + first + ", pagesize=" + pageSize
	// + ", sortfield=" + sortField + " sortorder="
	// + sortOrder + " filter:" + filters);
	// int start = first;
	// int end = first + pageSize;
	//
	// List<Customer> data = new ArrayList<Customer>();
	// customers = customerService.searchCustomer(
	// start, end);
	// // filter
	// for (Customer customer : customers) {
	// boolean match = true;
	//
	// for (Iterator<String> it = filters.keySet().iterator(); it
	// .hasNext();) {
	// try {
	// String filterProperty = it.next();
	// String filterValue = filters
	// .get(filterProperty);
	// String fieldValue = String.valueOf(customer
	// .getClass().getField(filterProperty)
	// .get(customer));
	// log.info("filter==" + filterValue);
	// if (filterValue == null
	// || fieldValue.startsWith(filterValue)) {
	// match = true;
	// } else {
	// match = false;
	// break;
	// }
	// } catch (Exception e) {
	// match = false;
	// }
	// }
	//
	// if (match) {
	// data.add(customer);
	// }
	// }
	//
	// // rowCount
	// int dataSize = data.size();
	// this.setRowCount(customerService.getCountAllCustomer());
	//
	// // paginate
	// if (dataSize > pageSize) {
	// try {
	// return data.subList(first, first + pageSize);
	// } catch (IndexOutOfBoundsException e) {
	// return data.subList(first, first
	// + (dataSize % pageSize));
	// }
	// } else {
	// return data;
	// }
	//
	// }
	//
	// @Override
	// public void setRowIndex(final int rowIndex) {
	// if (rowIndex == -1 || getPageSize() == 0) {
	// super.setRowIndex(-1);
	// } else {
	// super.setRowIndex(rowIndex % getPageSize());
	// }
	// }
	//
	// };
	// }
	//
	// return lazyDataModel;
	// }

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
		if (customers.isEmpty())
			customers = new ArrayList<Customer>();

		return customers;
	}

	/**
	 * Methode button search
	 */
	public void search() {
		getModel();
	}

	// Column List
	public List<LabelValueBean> getColumnList() {
		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.customerCode"), "code"));
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.customerName"), "name"));
		return columnList;
	}

	// Grade List
	public List<LabelValueBean> getGradeList() {
		List<LabelValueBean> gradeList = new ArrayList<LabelValueBean>();
		gradeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.gradeA"), "A"));
		gradeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.gradeB"), "B"));
		gradeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.gradeC"), "C"));
		gradeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.gradeD"), "D"));
		return gradeList;
	}

	/**
	 * Method when click add button in list view
	 */
	public void prepareAdd() {
		this.setCode(generateCode.generateCustomerCode());
	}

	public String goInputPage() {
		return "/pages/master/customerInput";
	}

	public String goListPage() {
		return "/pages/master/customerList";
	}

	/**
	 * Tambah customer data into database untuk commandbutton di halaman jsf
	 */
	public void saveCustomer() {
		try {
			Customer customer = new Customer();
			customer.setCode(generateCode.generateCustomerCode());
			customer.setAddress(this.getAddress());
			customer.setGrade(this.getGrade());
			customer.setTermOfPayment(this.isTermOfPayment() ? 1 : 0);
			customer.setCreatedDate(new Date());
			// for(int i=0; i<10000; i++){
			customer.setName(this.getName());
			customerService.save(customer);
			// }
			clearForm();
		} catch (Exception e) {
			log.warn(e.toString(), e);
			e.printStackTrace();
		}
	}

	/**
	 * Method pada saat update screen
	 */
	public String prepareUpdate() {
		log.info("prepare for update customer...");
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("customerIdParam");
		log.info("ID==" + id);
		Customer customer = customerService.searchCustomerById(new Long(id));
		log.info("CUST ID==" + customer.getCustomerId());
		this.setCustId("" + customer.getCustomerId());
		this.setCode(customer.getCode());
		this.setName(customer.getName());
		this.setTermOfPayment(customer.getTermOfPayment() == 1 ? true : false);
		this.setGrade(customer.getGrade() != null ? customer.getGrade() : "");
		this.setAddress(customer.getAddress());
		log.info("prepare for update customer end...");
		return null;
	}

	/**
	 * Edit customer data ke database untuk commandButton di halaman jsf
	 */
	public void updateCustomer() {
		log.info("Update Customer Begin");

		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("custId");
		Customer customer = customerService.searchCustomerById(new Long(id));
		customer.setCode(customer.getCode());
		customer.setName(this.getName());
		customer.setAddress(this.getAddress());
		customer.setGrade(this.getGrade());
		customer.setTermOfPayment(this.isTermOfPayment() ? 1 : 0);
		customerService.update(customer);
		clearForm();
		log.info("Update Customer End");

	}

	/**
	 * Delete
	 */
	public String deleteCustomer() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("customerIdParam");
		Customer customer = customerService.searchCustomerById(new Long(id));
		customerService.delete(customer);
		return null;
	}

	/**
	 * clear form values
	 */
	private void clearForm() {
		setName("");
		setAddress("");
	}

	public Customer[] getSelectedCustomers() {
		return selectedCustomers;
	}

	public void setSelectedCustomers(Customer[] selectedCustomers) {
		this.selectedCustomers = selectedCustomers;
	}

	public CustomerDataModel getModel() {
		customerDataModel = new CustomerDataModel(getCustomerList());
		return customerDataModel;
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

	public List<Customer> getFilteredCustomers() {
		return filteredCustomers;
	}

	public void setFilteredCustomers(List<Customer> filteredCustomers) {
		this.filteredCustomers = filteredCustomers;
	}

}
