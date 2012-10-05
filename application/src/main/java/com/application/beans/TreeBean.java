package com.application.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.service.UserService;

@ManagedBean(name ="treeBean")
@RequestScoped
public class TreeBean implements Serializable {
	
	private static final long serialVersionUID = -8570149161349805862L;
	private static final Logger log = Logger.getLogger(TreeBean.class);
	
	private TreeNode parent;
	
	private TreeNode ch;
	
	private String includePage;
	
	@ManagedProperty(value ="#{menuService}")
	private MenuService menuService;
	
	@ManagedProperty(value ="#{userService}")
	private UserService userService;
	
	public TreeBean() {
		/*root = new DefaultTreeNode("root", null);		

		// Documents
		TreeNode parent = new DefaultTreeNode(new Menu("Master", "-",
				"Folder"), root);
		
		TreeNode customerList = new DefaultTreeNode("page", new Menu(
				"Customer", "/pages/master/customerList.xhtml",
				"Word Document"), parent);
		
		
		TreeNode parent2 = new DefaultTreeNode(new Menu("Transaksi", "-",
				"Folder"), root);
		
		TreeNode puchase= new DefaultTreeNode("page", new Menu(
				"Purchase Order", "/pages/master/customerList.xhtml",
				"Word Document"), parent2);
*/		
	}
	
	
	public void init(){
		parent = new DefaultTreeNode("Root", null);	
		buildMenu(parent,  ch, "root");
	}
	

	private void buildMenu(TreeNode parent,  TreeNode child, String rightName) {
		
		List<Menu> menus = menuService.getMenuByParent(rightName);
        for (Menu m : menus) {
            TreeNode node = new DefaultTreeNode(m, null);
            if (child == null) {
            	parent.addChild(node);
            } else {
            	child.addChild(node);
            }
            buildMenu(parent, node, m.getMenuCode());
        }
     
    }
	

	public TreeNode getRoot() {
		return parent;
	}
	
	
	public String getIncludePage() {
		return includePage;
	}

	public void setIncludePage(String includePage) {
		this.includePage = includePage;
	}

	public void onNodeSelect(NodeSelectEvent event) {			  
		String type = event.getTreeNode().getType().toString();
		try{
		if (type.equalsIgnoreCase("page")) {			
			Menu menu = (Menu) event.getTreeNode().getData();
			System.out.println("Page == "+ menu.getMenuUrl());
			setIncludePage(menu.getMenuUrl());
			 FacesContext.getCurrentInstance().getExternalContext().redirect(menu.getMenuUrl());
		} else {
			setIncludePage("");
		}
		System.out.println("include page=="+includePage);
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
