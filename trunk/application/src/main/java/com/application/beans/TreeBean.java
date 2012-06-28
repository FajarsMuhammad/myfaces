package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Document;
import com.application.model.Menu;
import com.application.service.MenuService;

public class TreeBean implements Serializable {
	
	private static final long serialVersionUID = -8570149161349805862L;
	private static final Logger log = Logger.getLogger(TreeBean.class);
	
	private TreeNode root;
	
	private String includePage;
	
	private MenuService menuService;
	
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
		List<Menu> menus =  menuService.searchMenu();
		//buildMenu(menus, root);
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
		

		
	}
	
	private void buildMenu(List<Menu> menus, TreeNode pai) {
        for (Menu m : menus) {
            TreeNode noFilho = new DefaultTreeNode(new Menu(m.getName(), m.getUrl(), m.getType()), null);
            /*if (pai == null) {
                root.addChild(noFilho);
            } else {
                pai.addChild(noFilho);
            }*/
            if(m.getKind().equals(noFilho.getType())){
            	log.debug("masuk default "+noFilho.getType());
            	root.addChild(noFilho);            	
            }else{
            	for(TreeNode tree : pai.getChildren()){
            		log.debug(tree.getData());
            	}
            	
            	 pai.addChild(noFilho);
            }
            buildMenu(m.getMenuFiles(), noFilho);

        }
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
		log.debug("type==="+type);
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
	
	

}
