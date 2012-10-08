package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import com.application.datamodel.RoleDataModel;
import com.application.model.Role;
import com.application.service.RoleService;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "roleBean")
@SessionScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(RoleBean.class);

	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;

	private Role current = new Role();
	private String searchColumn;
	private String searchValue;
	
	private LazyDataModel<Role> lazyModel; 
	private Role selectedRoles;
	
	

	public String goInputPage() {
		return "/pages/setup/roleInput";
	}

	public String goListPage() {
		return "/pages/setup/roleList";
	}

	public void prepareAdd() {
		
	}
	
	public List<LabelValueBean> getColumnList() {
		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.roleShortDescription"), "roleShortDescription"));
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.roleLongDescription"), "roleLongDescription"));
		return columnList;
	}
	
	public List<Role> getRoleList() {
		List<Object> columnList = null;
		List<Object> valueList = null;
		List<Role> roles = new ArrayList<Role>();

		if (searchValue != null && !searchValue.trim().equals("")) {
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue);
		}

		roles = roleService.searchRole(columnList, valueList);
		if (roles.isEmpty())
			roles = new ArrayList<Role>();

		return roles;
	}
	
	public void search() {
		getLazyModel();
	}
	

	/**
	 * Method save
	 */
	public void saveOrUpdate() {
		try {
			if (current.getId() == 0) {
				log.info("Save start >>>");
				
				roleService.save(current);
				
				log.info("Save end >>>");
			} else {
				log.info("Update start >>>");
				
				roleService.update(current);
				
				log.info("Update End >>>");
			}
			current = new Role();
		} catch (Exception e) {
			current = new Role();
			log.error(e.toString(), e);
		}
	}
	

	/**
	 * Method pada saat update screen
	 */
	public void prepareUpdate() {
		log.info("prepare for update role...");

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("roleIdParam");
		Role role = roleService.getById(new Long(id));

		current.setId(role.getId());
		current.setRoleShortDescription(role.getRoleShortDescription());
		current.setRoleLongDescription(role.getRoleLongDescription());

		log.info("prepare for update role end...");
	}
	

	/**
	 * Delete
	 */
	public String delete() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("roleIdParam");
		Role role = roleService.getById(new Long(id));
		roleService.delete(role);
		return null;
	}
	
	//generate getter setter
	
	public LazyDataModel<Role> getLazyModel() {
		lazyModel = new RoleDataModel(getRoleList());
		return lazyModel;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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

	public Role getCurrent() {
		return current;
	}

	public void setCurrent(Role current) {
		this.current = current;
	}


	public Role getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Role selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

}
