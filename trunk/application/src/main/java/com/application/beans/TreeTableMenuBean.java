package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "treeTableMenuBean")
@SessionScoped
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

	private TreeNode ch;

	private Menu selectedMenu;

	public void init() {
		parent = new DefaultTreeNode("parent", null);
		buildMenu(parent, ch, "0000ROOT");
	}

	private void buildMenu(TreeNode parent, TreeNode child, String rightName) {

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
			parentList.add(new LabelValueBean(menu.getMenuCode() + " - "
					+ menu.getMenuName(), menu.getMenuCode()));
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

	public void editTest() {
		System.out.println("Inpace work");
		log.info("Inplaceee wooorrkkk");
	}

	/**
	 * Method save
	 */
	public void saveOrUpdate() {
		try {
			if (current.getId() == 0) {
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

		log.info("prepare for update menu end...");
	}
	

	/**
	 * Delete
	 */
	public String delete() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("menuIdParam");
		Menu menu = menuService.getById(new Long(id));
		menuService.delete(menu);
		return null;
	}
	
	//generate getter setter

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

}
