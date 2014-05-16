package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import com.application.datamodel.CustomerDataModel;
import com.application.datamodel.CustomerLazyDataModel;
import com.application.model.Customer;
import com.application.service.CustomerService;
import com.application.utility.GenerateCode;
import com.application.utility.JsfUtil;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "customerBean")
@RequestScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 3051005476848367530L;
	private static final Logger log = Logger.getLogger(CustomerBean.class);

	private Customer current = new Customer();
	private Customer detailCustomer = new Customer();

	private String searchColumn;
	private String searchValue;

	private int first;
	private int pageSize;

	@ManagedProperty(value = "#{customerService}")
	private CustomerService customerService;

	@ManagedProperty(value = "#{generateCode}")
	private GenerateCode generateCode;

	private CustomerDataModel customerDataModel;
	private Customer[] selectedCustomers;

	// @ManagedProperty(value = "#{lazyDataModel}")
	private LazyDataModel<Customer> lazyDataModel;

	private List<Customer> filteredCustomers;

	public LazyDataModel<Customer> getLazyDataModel() {
		if (lazyDataModel == null) {
			lazyDataModel = new CustomerLazyDataModel(getCustomerList());
		}
		return lazyDataModel;
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
		customers = customerService.searchCustomer(columnList, valueList,
				first, pageSize);
		if (customers.isEmpty())
			customers = new ArrayList<Customer>();

		return customers;
	}

	/**
	 * Methode button search
	 */
	public void search() {
		getModel();
		// getLazyDataModel();
	}

	public List<LabelValueBean> getColumnList() {
		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.customerCode"), "code"));
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.customerName"), "name"));
		return columnList;
	}

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
		current.setCode(generateCode.generateCustomerCode());
	}

	public String goInputPage() {
		return "/pages/master/customerInput";
	}

	public String goListPage() {
		return "/pages/master/customerList";
	}

	/**
	 * Method pada saat update screen
	 */
	public void prepareUpdate() {
		log.info("prepare for update customer...");
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("customerIdParam");
		log.info("ID==" + id);
		Customer customer = customerService.searchCustomerById(new Long(id));
		current.setId(customer.getId());
		current.setCode(customer.getCode());
		current.setName(customer.getName());
		current.setTermOfPayment(customer.isTermOfPayment());
		current.setGrade(customer.getGrade() != null ? customer.getGrade() : "");
		current.setAddress(customer.getAddress());
		log.info("prepare for update customer end...");
	}

	public void saveOrUpdate() {
		try {
			log.info("IDS====" + current.getId());
			log.info("IDS====" + this.current.getCode());
			if (current.getId() == null || current.getId() == 0) {
				log.info("Save start >>>");
				current.setCode(generateCode.generateCustomerCode());
				current.setCreatedDate(new Date());
				customerService.save(current);
			} else {
				log.info("Update start >>>");
				Customer customer = customerService.searchCustomerById(current
						.getId());
				current.setCode(customer.getCode());
				current.setCreatedDate(new Date());
				customerService.update(current);
			}
			current = new Customer();
		} catch (Exception e) {
			current = new Customer();
			JsfUtil.addErrorMessage(ResourceHelper.getResources("error.save"));
			log.error(e.toString(), e);
		}
	}

	/**
	 * Delete
	 */
	public void delete() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String id = params.get("customerIdParam");
			Customer customer = customerService
					.searchCustomerById(new Long(id));
			customerService.delete(customer);
			current = new Customer();
		} catch (Exception e) {
			current = new Customer();
			e.printStackTrace();
		}
	}

	public void deleteSelected() {
		try {
			List<Customer> customers = new ArrayList<Customer>();
			if(selectedCustomers!=null){
				for(int i =0; i<selectedCustomers.length; i++){
					Customer customer = (Customer)selectedCustomers[i];
					customers.add(customer);
				}
			}
			customerService.deleteAll(customers);
			current = new Customer();
		} catch (Exception e) {
			current = new Customer();
			e.printStackTrace();
		}
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

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Customer getCurrent() {
		return current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
	}

	public Customer getDetailCustomer() {
		return detailCustomer;
	}

	public void setDetailCustomer(Customer detailCustomer) {
		this.detailCustomer = detailCustomer;
	}

}
