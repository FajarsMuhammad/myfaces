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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.MenuModel;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.service.MenuService;

@ManagedBean(name = "menubarBean")
@SessionScoped
public class MenubarBean implements Serializable {

	private static final long serialVersionUID = -6096920035544826739L;

	private MenuModel simpleMenuModel = new DefaultMenuModel();
	
	//private Submenu parent;
	
	private Submenu submenu;

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
		/*
		// setup
		Submenu submenuSetup = new Submenu();
		submenuSetup.setLabel("Setup");
		submenuSetup.setIcon("ui-icon ui-icon-home");

		MenuItem menuItemSetup = new MenuItem();
		menuItemSetup.setValue("Menu");
		menuItemSetup.setUrl("#");
		submenuSetup.getChildren().add(menuItemSetup);
		simpleMenuModel.addSubmenu(submenuSetup);
		// end setup

		// master
		Submenu submenuMaster = new Submenu();
		submenuMaster.setLabel("Master");
		submenuMaster.setIcon("ui-icon ui-icon-document");

		MenuItem menuItemMaster = new MenuItem();
		menuItemMaster.setValue("Customer");
		menuItemMaster.setUrl("/pages/master/customerList.jsf");// /pages/master/customerList.xhtml
		submenuMaster.getChildren().add(menuItemMaster);
		simpleMenuModel.addSubmenu(submenuMaster);
		// end master

		// transaction
		Submenu submenuTransaction = new Submenu();
		submenuTransaction.setLabel("Transaction");
		submenuTransaction.setIcon("ui-icon ui-icon-document");

		MenuItem menuItemTransaction = new MenuItem();
		menuItemTransaction.setValue("Purchase Order");
		menuItemTransaction.setUrl("/pages/master/customerList.jsf");// /pages/master/customerList.xhtml
		submenuTransaction.getChildren().add(submenuTransaction);

		simpleMenuModel.addSubmenu(submenuTransaction);
		// end transaction

		// quit
		FacesContext ctx = FacesContext.getCurrentInstance();
		String path = ctx.getExternalContext().getRequestContextPath();

		MenuItem menuItemQuit = new MenuItem();
		menuItemQuit.setValue("Quit");
		menuItemQuit.setIcon("ui-icon ui-icon-close");
		menuItemQuit.setUrl(path + "/j_spring_security_logout");
		simpleMenuModel.addMenuItem(menuItemQuit);
		// end quit
		
		*/
	}
	
	public void init(){
		
		buildMenu(submenu, "root");
		FacesContext ctx = FacesContext.getCurrentInstance();
		String path = ctx.getExternalContext().getRequestContextPath();

		MenuItem menuItemQuit = new MenuItem();
		menuItemQuit.setValue("Quit");
		menuItemQuit.setIcon("ui-icon ui-icon-close");
		menuItemQuit.setUrl(path + "/j_spring_security_logout");
		simpleMenuModel.addMenuItem(menuItemQuit);
	}

	private void buildMenu(Submenu submenu, String rightName) {
		//simpleMenuModel.addSubmenu(null);
		List<Menu> menus = menuService.getMenuByParent(rightName);
		for (Menu m : menus) {			
			//simpleMenuModel.addSubmenu(submenu);
			Submenu parent = new Submenu();
			parent.setLabel(m.getName());
			MenuItem menuItem = new MenuItem();
			if(submenu == null){					
				simpleMenuModel.addSubmenu(parent);
			}else{				
				menuItem.setValue(m.getName());
				menuItem.setUrl(m.getUrl());
				submenu.getChildren().add(menuItem);
				simpleMenuModel.addSubmenu(submenu);
			}
			buildMenu(parent, m.getRightName());
		}
		
	}

}
