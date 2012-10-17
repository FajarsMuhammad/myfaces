package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.application.model.Customer;
import com.application.model.Menu;
import com.application.model.Role;
import com.application.service.MenuService;
import com.application.service.RoleMenuService;
import com.application.service.RoleService;

@ManagedBean(name = "roleMenuBean")
@ViewScoped
public class RoleMenuBean implements Serializable {

	private static final long serialVersionUID = 3928654083395896418L;
	
	private static final Logger log = Logger.getLogger(RoleMenuBean.class);

	private Role selectedRoles;

	private Menu[] selectedMenus;

	private DashboardModel dashboardModel;

	@ManagedProperty(value = "#{roleMenuService}")
	private RoleMenuService roleMenuService;

	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;

	@ManagedProperty(value = "#{menuService}")
	private MenuService menuService;

	public DashboardModel getDashboardModel() {
		return dashboardModel;
	}

	@PostConstruct
	public void init() {
		dashboardModel = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();

		column1.addWidget("rolePanel");
		column2.addWidget("menuPanel");

		dashboardModel.addColumn(column1);
		dashboardModel.addColumn(column2);

	}

	public List<Role> getRoleList() {
		List<Role> roles = null;

		roles = roleService.searchRole();
		if (roles.isEmpty())
			roles = new ArrayList<Role>();

		return roles;
	}

	public List<Menu> getMenuList() {
		List<Menu> menus = null;

		menus = menuService.searchMenu();
		if (menus.isEmpty())
			menus = new ArrayList<Menu>();

		return menus;
	}

	public void addRoleMenu() {
		try {
			if (selectedRoles != null && selectedMenus !=null) {
				Role role = (Role) selectedRoles;
				Menu menu = null;
				List<Menu> menus = new ArrayList<Menu>();
				for(int i = 0; i < selectedMenus.length; i++){
					menu = (Menu)selectedMenus[i];
					//menus.add(menu);
					log.info("Dataaaa === "+role.getRoleShortDescription()+ "  "+menu.getMenuName() );
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RoleMenuService getRoleMenuService() {
		return roleMenuService;
	}

	public void setRoleMenuService(RoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Role selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public Menu[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(Menu[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
