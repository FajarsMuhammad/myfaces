package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.application.model.Customer;
import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name="menuBean")
@RequestScoped
public class MenuBean implements Serializable{
	
	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(MenuBean.class);
	
	@ManagedProperty(value = "#{menuService}")
	private MenuService menuService;

	private String menuId;
	private String parent;
	private String name;    
    private String url;    
    private String type;    
    private String kind;    
    private String rightName;
    private LazyDataModel<Menu> lazyModel;
    private String searchColumn;
	private String searchValue;
    
    /*public LazyDataModel<Menu> getLazyModel() {
    	//if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Menu>() {
				
				@Override
				public List<Menu> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
					log.info("first=" + first + ", pagesize=" + pageSize + ", sortfield=" + sortField + " sortorder=" + sortOrder + " filter:" + filters);	
					int start = first;
                	int end = first + pageSize;
                	
                	List<Object> columnList = null;
            		List<Object> valueList = null;
            		
            		if (searchValue != null && !searchValue.trim().equals("")) {
            			columnList = new ArrayList<Object>();
            			valueList = new ArrayList<Object>();
            			columnList.add(searchColumn);
            			valueList.add(searchValue);
            		}

 //               	List<Menu> menus = menuService.searchMenu(columnList, valueList, start, end);
 //               	this.setRowCount(menuService.getMenuCount(columnList, valueList));
//                	List<Employee> empList = getClassicModelsService().findEmployees(start, end);
//                		this.setRowCount(getClassicModelsService().getEmployeeCount());
 //               	System.out.println("menus=="+menus.size());
//                    return menus;
				}

			};
    	//}
    	
    	return lazyModel;
    }*/
    
 // Column List
 	public List<LabelValueBean> getColumnList() {
 		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
 		columnList.add(new LabelValueBean(ResourceHelper.getResources("label.customerCode"), "code"));
 		columnList.add(new LabelValueBean(ResourceHelper.getResources("label.customerName"), "name"));
 		return columnList;
 	}
 	
 	/**
	 * Tambah customer data into database untuk commandbutton di halaman jsf
	 */
	public void save() {
		try{
			Menu menu = new Menu();
			menu.setParent(getParent());
			
			for(int i=0; i<10000; i++){
				menu.setName(getName()+i);
				//menuService.save(menu);
			}		
			clearForm();
		}catch (Exception e) {
			log.warn(e.toString(), e);
		}
	}

	/**
	 * clear form values
	 */
	private void clearForm() {
		setName("");
		setParent("");
	}
    //getter and setter
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
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
    
    
}
