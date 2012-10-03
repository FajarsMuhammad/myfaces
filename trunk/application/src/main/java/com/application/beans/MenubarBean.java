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

import com.application.model.Menu2;
import com.application.service.Menu2Service;

@ManagedBean(name = "menubarBean")
@SessionScoped
public class MenubarBean implements Serializable {


	private static final long serialVersionUID = 3568928318014489011L;

	private MenuModel simpleMenuModel;// = new DefaultMenuModel();

	@ManagedProperty(value = "#{menu2Service}")
	private Menu2Service menu2Service;

	public Menu2Service getMenu2Service() {
		return menu2Service;
	}

	public void setMenu2Service(Menu2Service menu2Service) {
		this.menu2Service = menu2Service;
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
		List<Menu2> menuList = menu2Service.getMenuByUser(user);
		for (Menu2 m : menuList) {
			if (m.getMenuLevel() == 1) {
				Submenu parent = new Submenu();
				parent.setLabel(m.getMenuName());
				parent.setIcon("ui-icon ui-icon-document");
				simpleMenuModel.addSubmenu(parent);
				buildChildMenu(parent, m.getMenuCode(), user);
			}

		}
	}

	public void buildChildMenu(Submenu submenu, String menuCode, String user) {
		List<Menu2> menuList = menu2Service.getMenuByUser(user);
		List<Menu2> menus = menu2Service.getMenuByParent(menuCode);
		for (Menu2 menu2 : menuList) {
			for (Menu2 m : menus) {
				if (menu2.getMenuCode().equals(m.getMenuCode())) {
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

	public void buildChildChildMenu(Submenu submenu, Submenu sub,
			String menuCode, String user) {
		List<Menu2> menuList = menu2Service.getMenuByUser(user);
		List<Menu2> menus = menu2Service.getMenuByParent(menuCode);
		for (Menu2 menu2 : menuList) {
			for (Menu2 m : menus) {
				if (menu2.getMenuCode().equals(m.getMenuCode())) {
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
