package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.model.Role;
import com.application.model.RoleMenu;
import com.application.service.MenuService;
import com.application.service.RoleMenuService;
import com.application.service.RoleService;

@ManagedBean(name = "roleMenuBean")
@RequestScoped
public class RoleMenuBean implements Serializable {

	private static final long serialVersionUID = 3928654083395896418L;

	private static final Logger log = Logger.getLogger(RoleMenuBean.class);

	private Role selectedRoles;

	private Menu[] selectedMenus;

	private DashboardModel dashboardModel;

	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private RoleMenu current = new RoleMenu();

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

	public List<Menu> getMenuChildList(String menuCode) {
		List<Menu> menus = null;

		menus = menuService.getMenuByParent(menuCode);

		if (menus.isEmpty())
			menus = new ArrayList<Menu>();

		return menus;
	}

	public void onSelect(SelectEvent event) {
		if (selectedRoles != null) {
			Role role = (Role) selectedRoles;
			DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
					.getViewRoot().findComponent(":formList:menuTable");
			List<Menu> menus = (List<Menu>) dataTable.getValue();
			List<RoleMenu> roleMenus = roleMenuService
					.searchRoleMenuByRole(role.getId());

			for (Menu menu : menus) {
				for (RoleMenu roleMenu : roleMenus) {
					if (menu.getId().equals(roleMenu.getMenu().getId())) {
						log.info("Row === " + menu.getMenuName());
						checked.put(roleMenu.getMenu().getId(), true);
					}
				}
			}
		}
	}

	public void addRoleMenu() {
		try {
			DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
					.getViewRoot().findComponent("formList:menuTable");

			if (selectedRoles != null) {
				Role role = (Role) selectedRoles;
				List<Menu> menus = (List<Menu>) dataTable.getValue();

				List<RoleMenu> roleMenus = roleMenuService
						.searchRoleMenuByRole(role.getId());

				// new role
				if (roleMenus.isEmpty()) {
					for (Menu menu : menus) {
						if (checked.get(menu.getId()).booleanValue()) {
							current.setMenu(menu);
							current.setRole(role);
							roleMenuService.save(current);
						}
					}
				}

				// delete
				for (RoleMenu roleMenuDb : roleMenus) {
					boolean isDeleted = true;
					for (Menu menu : menus) {
						if (checked.get(menu.getId()).booleanValue()) {
							if (roleMenuDb.getMenu().getId()
									.equals(menu.getId())) {
								isDeleted = false;
							}
							RoleMenu roleMenu = roleMenuService
									.searchRoleMenuByRoleAndMenu(role.getId(),
											menu.getId());
							if (roleMenu == null) {
								current.setMenu(menu);
								current.setRole(role);
								roleMenuService.save(current);
							}
						}
					}
					if (isDeleted) {
						roleMenuService.delete(roleMenuDb);
					}
				}

			}
			current = new RoleMenu();
		} catch (Exception e) {
			current = new RoleMenu();
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

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public RoleMenu getCurrent() {
		return current;
	}

	public void setCurrent(RoleMenu current) {
		this.current = current;
	}

}
