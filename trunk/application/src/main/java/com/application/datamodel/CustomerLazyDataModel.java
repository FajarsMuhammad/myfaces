package com.application.datamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.application.model.Customer;
import com.application.service.CustomerService;

public class CustomerLazyDataModel extends LazyDataModel<Customer> {

	private Logger log = Logger.getLogger(CustomerLazyDataModel.class);
	private static final long serialVersionUID = 1L;
	private List<Customer> customers;
	private CustomerService customerService;

	@Override
	public List<Customer> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		log.info("first=" + first + ", pagesize=" + pageSize
				 + ", sortfield=" + sortField + " sortorder="
				 + sortOrder + " filter:" + filters);
		
		List<Customer> data = new ArrayList<Customer>();
		
		customers = customerService.searchCustomer(first, pageSize);
		// filter
		for (Customer customer : customers) {
			boolean match = true;
			
			for (Iterator<String> it = filters.keySet().iterator(); it
					.hasNext();) {
				
				try {
					log.info("filter==" +  String.valueOf(customer.getClass()
							.getField(it.next()).get(customer)));
					String filterProperty = it.next();
					String filterValue = filters.get(filterProperty);
					String fieldValue = String.valueOf(customer.getClass()
							.getField(filterProperty).get(customer));
					log.info("filter==" + filterValue);
					if (filterValue == null
							|| fieldValue.startsWith(filterValue)) {
						match = true;
					} else {
						match = false;
						break;
					}
				} catch (Exception e) {
					match = false;
				}
			}

			if (match) {
				data.add(customer);
			}
		}

		// sort
		// if(sortField != null) {
		// Collections.sort(data, new LazySorter(sortField, sortOrder));
		// }

		// rowCount
		int dataSize = data.size();
		this.setRowCount(customerService.getCountAllCustomer());

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

	@Override
	public Object getRowKey(Customer customer) {
		return customer.getCustomerId();
	}

	@Override
	public Customer getRowData(String customerId) {
		Integer id = Integer.valueOf(customerId);
		for (Customer customer : customers) {
			if (id.equals(customer.getCustomerId())) {
				return customer;
			}
		}

		return null;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
