package com.application.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.application.model.Menu;
import com.application.service.MenuService;

@ManagedBean(name = "menubarBean")
@SessionScoped
public class MenubarBean implements Serializable {

	private static final long serialVersionUID = 3568928318014489011L;

	private MenuModel simpleMenuModel;

	@ManagedProperty(value = "#{menuService}")
	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public MenuModel getSimpleMenuModel() {
		return simpleMenuModel;
	}

	public MenubarBean() {
	}

	public void init() {

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String user = userDetails.getUsername();

		simpleMenuModel = new DefaultMenuModel();
		buildMenu(user);

		FacesContext ctx = FacesContext.getCurrentInstance();
		String path = ctx.getExternalContext().getRequestContextPath();

		MenuItem menuItemQuit = new MenuItem();
		menuItemQuit.setValue("Quit");
		menuItemQuit.setIcon("ui-icon ui-icon-close");
		menuItemQuit.setUrl(path + "/j_spring_security_logout");
		simpleMenuModel.addMenuItem(menuItemQuit);
	}

	private void buildMenu(String user) {
		List<Menu> menuList = menuService.getMenuByUser(user);
		for (Menu m : menuList) {
			if (m.getMenuLevel() == 1) {
				Submenu parent = new Submenu();
				parent.setLabel(m.getMenuName());
				parent.setIcon("ui-icon ui-icon-document");
				simpleMenuModel.addSubmenu(parent);
				buildChildMenu(parent, m.getMenuCode(), user);
			}
		}
	}

	private void buildChildMenu(Submenu submenu, String menuCode, String user) {
		List<Menu> menuList = menuService.getMenuByUser(user);
		List<Menu> menus = menuService.getMenuByParent(menuCode);
		for (Menu menu : menuList) {
			for (Menu m : menus) {
				if (menu.getId() == m.getId()) {
					if (m.getMenuUrl().equals("#") && m.getMenuLevel() == 2) {
						Submenu subsub = new Submenu();
						subsub.setLabel(m.getMenuName());
						subsub.setIcon("ui-icon ui-icon-document");
						submenu.getChildren().add(subsub);
						simpleMenuModel.addSubmenu(submenu);
						buildChildChildMenu(submenu, subsub, m.getMenuCode(),
								user);
					} else {
						MenuItem menuItem = new MenuItem();
						menuItem.setValue(m.getMenuName());
						menuItem.setUrl(m.getMenuUrl());
						submenu.getChildren().add(menuItem);
						simpleMenuModel.addSubmenu(submenu);
					}
				}
			}
		}
	}

	private void buildChildChildMenu(Submenu submenu, Submenu sub,
			String menuCode, String user) {
		List<Menu> menuList = menuService.getMenuByUser(user);
		List<Menu> menus = menuService.getMenuByParent(menuCode);
		for (Menu menu : menuList) {
			for (Menu m : menus) {
				if (menu.getId() == m.getId()) {
					MenuItem menuItem = new MenuItem();
					menuItem.setValue(m.getMenuName());
					menuItem.setUrl(m.getMenuUrl());
					sub.getChildren().add(menuItem);
					submenu.getChildren().add(sub);
					simpleMenuModel.addSubmenu(submenu);
				}
			}
		}
	}

}
