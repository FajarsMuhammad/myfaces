package com.application.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "menubarBean")
@SessionScoped
public class MenubarBean implements Serializable{

	private static final long serialVersionUID = -6096920035544826739L;
	
	private MenuModel simpleMenuModel = new DefaultMenuModel();
	
	public MenuModel getSimpleMenuModel() {
		return simpleMenuModel;
	}

	public MenubarBean(){
		Submenu submenuSetup = new Submenu();
		submenuSetup.setLabel("Setup");
		submenuSetup.setIcon("ui-icon ui-icon-home");
		
		MenuItem menuItemSetup = new MenuItem();
		menuItemSetup.setValue("Menu");
		menuItemSetup.setUrl("#");
		submenuSetup.getChildren().add(menuItemSetup);
		simpleMenuModel.addSubmenu(submenuSetup);
        
		Submenu submenuMaster = new Submenu();
		submenuMaster.setLabel("Master");
		submenuMaster.setIcon("ui-icon ui-icon-document");
		
		MenuItem menuItemMaster = new MenuItem();
		menuItemMaster.setValue("Customer");
		menuItemMaster.setUrl("/pages/master/customerList.xhtml");
		submenuMaster.getChildren().add(menuItemMaster);
		
		simpleMenuModel.addSubmenu(submenuMaster);
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        String path = ctx.getExternalContext().getRequestContextPath();

        MenuItem menuItemQuit = new MenuItem();
        menuItemQuit.setValue("Quit");
        menuItemQuit.setIcon("ui-icon ui-icon-close");
        menuItemQuit.setUrl(path+"/j_spring_security_logout");
        simpleMenuModel.addMenuItem(menuItemQuit);
	}
	
	

}
