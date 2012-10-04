package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Menu2;
import com.application.service.Menu2Service;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "treeTableMenuBean")
@SessionScoped
public class TreeTableMenuBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(TreeTableMenuBean.class);

	@ManagedProperty(value = "#{menu2Service}")
	private Menu2Service menu2Service;

	/*private String id;
	private String menuCode;
	private String parentCode;
	private String menuName;
	private String menuUrl;
	private String menuType;
	private String systemMenu;
	private String menuLevel;*/
	private Menu2 current = new Menu2();;
	private String searchColumn;
	private String searchValue;

	private TreeNode parent;

	private TreeNode ch;

	private Menu2 selectedMenu;

	public void init() {
		parent = new DefaultTreeNode("parent", null);
		buildMenu(parent, ch, "0000ROOT");
	}

	private void buildMenu(TreeNode parent, TreeNode child, String rightName) {

		List<Menu2> menus = menu2Service.getMenuByParent(rightName);
		for (Menu2 m : menus) {
			TreeNode node = new DefaultTreeNode(m, null);
			if (child == null) {
				parent.addChild(node);
			} else {
				child.addChild(node);
			}
			buildMenu(parent, node, m.getMenuCode());
		}

	}

	// MenuType List
	public List<LabelValueBean> getMenuTypeList() {
		List<LabelValueBean> menuTypeList = new ArrayList<LabelValueBean>();
		menuTypeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.folder"), "1"));
		menuTypeList.add(new LabelValueBean(ResourceHelper
				.getResources("label.page"), "2"));
		return menuTypeList;
	}

	// parent List
	public List<LabelValueBean> getParentList() {
		List<LabelValueBean> parentList = new ArrayList<LabelValueBean>();
		List<Menu2> menu2s = menu2Service.searchMenu();
		for (Menu2 menu2 : menu2s) {
			parentList.add(new LabelValueBean(menu2.getMenuCode() + " - "
					+ menu2.getMenuName(), menu2.getMenuCode()));
		}
		return parentList;
	}

	public String goInputPage() {
		return "/pages/setup/menuInput";
	}

	public String goListPage() {
		return "/pages/setup/treeMenuList";
	}

	public void initialAdd() {
		// this.setCode(generateCode.generateMenuCode());
	}
	
	public void editTest() {
		System.out.println("Inpace work");
		log.info("Inplaceee wooorrkkk");
	}

	/**
	 * Method save
	 */
	public void save() {
		try {
			// clearForm();
		} catch (Exception e) {
			log.warn(e.toString(), e);
		}
	}

	/**
	 * Method pada saat update screen
	 */
	public void initialUpdate() {
		log.info("prepare for update menu...");
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("menuIdParam");
		log.info("ID==" + id);

		log.info("prepare for update menu end...");
		//return null;
	}

	/**
	 * Edit menu data ke database untuk commandButton di halaman jsf
	 */
	public void update() {
		log.info("Update Menu Begin");

		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("custId");

		log.info("Update Menu End");

	}

	/**
	 * Delete
	 */
	public String delete() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("menuIdParam");

		return null;
	}

	public Menu2Service getMenu2Service() {
		return menu2Service;
	}

	public void setMenu2Service(Menu2Service menu2Service) {
		this.menu2Service = menu2Service;
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

	public Menu2 getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(Menu2 selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public TreeNode getParent() {
		return parent;
	}

	public Menu2 getCurrent() {
		return current;
	}

	public void setCurrent(Menu2 current) {
		this.current = current;
	}

	

}
