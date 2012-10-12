package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.application.datamodel.RoleDataModel;
import com.application.model.Role;
import com.application.service.RoleService;
import com.application.utility.JsfUtil;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "roleBean")
@RequestScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(RoleBean.class);

	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;

	private Role current = new Role();
	private Role detailedRole = new Role();
	private String searchColumn;
	private String searchValue;
	
	private Role[] selectedRoles;
	private RoleDataModel roleDataModel;
	
	
	public RoleDataModel getRoleDataModel() {
		roleDataModel = new RoleDataModel(getRoleList());
		return roleDataModel;
	}

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
		getRoleDataModel();
	}
	

	/**
	 * Method save
	 */
	public void saveOrUpdate() {
		try {
			log.info("IDS===="+current.getId());
			if (current.getId() == 0) {
				log.info("Save start >>>");				
				roleService.save(current);				
			} else {
				log.info("Update start >>>");				
				roleService.update(current);				
			}
			current = new Role();
		} catch (Exception e) {
			current = new Role();
			JsfUtil.addErrorMessage(ResourceHelper.getResources("error.save"));
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
		log.info("ID == "+id);
		Role role = roleService.getById(new Long(id));
		log.info("role == "+role.getId());
		current.setId(role.getId());
		current.setRoleShortDescription(role.getRoleShortDescription());
		current.setRoleLongDescription(role.getRoleLongDescription());

		log.info("prepare for update role end...");
	}
	

	/**
	 * Delete
	 */
	public void delete() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("roleIdParam");
		log.info("id === "+id);
		log.info("ID === "+detailedRole.getId());
		Role role = roleService.getById(new Long(id));
		roleService.delete(role);
	}
	
	//generate getter setter
	

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

	public Role getDetailedRole() {
		return detailedRole;
	}

	public void setDetailedRole(Role detailedRole) {
		this.detailedRole = detailedRole;
	}

	public Role[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Role[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}



}
