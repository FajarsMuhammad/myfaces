package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "treeTableMenuBean")
@RequestScoped
public class TreeTableMenuBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(TreeTableMenuBean.class);

	@ManagedProperty(value = "#{menuService}")
	private MenuService menuService;

	/*
	 * private String id; private String menuCode; private String parentCode;
	 * private String menuName; private String menuUrl; private String menuType;
	 * private String systemMenu; private String menuLevel;
	 */
	private Menu current = new Menu();
	private String searchColumn;
	private String searchValue;

	private TreeNode parent;

	private TreeNode selectedNode;

	private Menu selectedMenu;

	@PostConstruct
	public void init() {
		buildTree("0000ROOT");
	}

	private TreeNode buildTree(String rightName) {
		parent = new DefaultTreeNode("parent", null);
		List<Menu> menus = menuService.getMenuByParent(rightName);
		for (Menu m : menus) {
			TreeNode tnChild = new DefaultTreeNode(m, parent);
			tnChild.setParent(parent);
			buildTreeRecursively(tnChild, m.getMenuCode());
		}
		return parent;
	}

	private void buildTreeRecursively(TreeNode currentNode, String rightName) {
		List<Menu> menus = menuService.getMenuByParent(rightName);
		for (Menu m : menus) {
			TreeNode tnChild = new DefaultTreeNode(m, currentNode);
			tnChild.setParent(currentNode);
			buildTreeRecursively(tnChild, m.getMenuCode());
		}
	}

	public List<LabelValueBean> getMenuTypeList() {
		List<LabelValueBean> menuTypeList = new ArrayList<LabelValueBean>();
		menuTypeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.folder"), "1"));
		menuTypeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.page"), "2"));
		return menuTypeList;
	}

	public List<LabelValueBean> getParentList() {
		List<LabelValueBean> parentList = new ArrayList<LabelValueBean>();
		List<Menu> menus = menuService.searchMenu();
		for (Menu menu : menus) {
			if (menu.getMenuType() == 1) {
				parentList.add(new LabelValueBean(menu.getMenuCode() + " - "
						+ menu.getMenuName(), menu.getMenuCode()));
			}
		}
		return parentList;
	}

	public String goInputPage() {
		return "/pages/setup/menuInput";
	}

	public String goListPage() {
		return "/pages/setup/treeMenuList";
	}

	public void prepareAdd() {

	}

	/**
	 * Method save
	 */
	public void saveOrUpdate() {
		try {
			if (current.getId() == null || current.getId() == 0) {
				log.info("Save");
				Menu parentMenu = menuService
						.getByCode(current.getParentCode());
				current.setMenuLevel(parentMenu.getMenuLevel() + 1);
				menuService.save(current);
			} else {
				Menu parentMenu = menuService
						.getByCode(current.getParentCode());
				current.setMenuLevel(parentMenu.getMenuLevel() + 1);
				menuService.update(current);
				log.info("Update");
			}
			current = new Menu();
		} catch (Exception e) {
			current = new Menu();
			log.error(e.toString(), e);
		}
	}

	/**
	 * Method pada saat update screen
	 */
	public void prepareUpdate() {
		log.info("prepare for update menu...");
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String id = params.get("menuIdParam");
			Menu menu = menuService.getById(new Long(id));

			current.setId(menu.getId());
			current.setParentCode(menu.getParentCode() != null ? menu
					.getParentCode() : "");
			current.setMenuCode(menu.getMenuCode());
			current.setMenuName(menu.getMenuName());
			current.setMenuUrl(menu.getMenuUrl());
			current.setMenuType(menu.getMenuType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("prepare for update menu end...");
	}

	/**
	 * Delete
	 */
	public String delete() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String id = params.get("menuIdParam");
			Menu menu = menuService.getById(new Long(id));
			menuService.delete(menu);
			current = new Menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// generate getter setter

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
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

	public Menu getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(Menu selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public TreeNode getParent() {
		return parent;
	}

	public Menu getCurrent() {
		return current;
	}

	public void setCurrent(Menu current) {
		this.current = current;
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		TreeNode node = event.getTreeNode();
		node.setExpanded(false);
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

}
