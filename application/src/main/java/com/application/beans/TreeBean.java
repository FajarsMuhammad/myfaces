package com.application.beans;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.service.UserService;

public class TreeBean implements Serializable {
	
	private static final long serialVersionUID = -8570149161349805862L;
	private static final Logger log = Logger.getLogger(TreeBean.class);
	
	private TreeNode root;
	
	private TreeNode parent;
	
	private String includePage;
	
	private MenuService menuService;
	
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
		root = new DefaultTreeNode("Root", null);	
		buildMenu(root,  parent, "root");
	}
	

	private void buildMenu(TreeNode root,  TreeNode node, String rightName) {
		log.info("Prepare Building Menu...");
		List<Menu> menus = menuService.getMenuByParent(rightName);
        for (Menu m : menus) {
            TreeNode child = new DefaultTreeNode(m.getKind(), new Menu(m.getName(), m.getUrl(), m.getType()), null);
            if (node == null) {
                root.addChild(child);
            } else {
            	node.addChild(child);
            }
            buildMenu(root, child, m.getRightName());
        }
        log.info("Building Menu Success...");
    }
	

	public TreeNode getRoot() {
		return root;
	}
	
	
	public String getIncludePage() {
		return includePage;
	}

	public void setIncludePage(String includePage) {
		this.includePage = includePage;
	}

	public void onNodeSelect(NodeSelectEvent event) {			  
		String type = event.getTreeNode().getType().toString();
		if (type.equalsIgnoreCase("page")) {			
			Menu menu = (Menu) event.getTreeNode().getData();
			setIncludePage(menu.getUrl());
		} else {
			setIncludePage("");
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
