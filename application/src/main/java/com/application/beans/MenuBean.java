package com.application.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.application.datamodel.MenuDataModel;
import com.application.datamodel.MenuDataModel;
import com.application.model.Menu;
import com.application.service.MenuService;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "menuBean")
@RequestScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(MenuBean.class);

	@ManagedProperty(value = "#{menuService}")
	private MenuService menuService;

	private String id;
	private String menuCode;
	private String parentCode;
	private String menuName;
	private String menuUrl;
	private String menuType;
	private String systemMenu;
	private String menuLevel;
	private String searchColumn;
	private String searchValue;

	private LazyDataModel<Menu> lazyModel;
	private MenuDataModel menuDataModel;
	private Menu[] selectedMenus;

	/**
	 * get all menu data from database
	 */
	public List<Menu> getMenuList() {
		List<Object> columnList = null;
		List<Object> valueList = null;
		List<Menu> menus = new ArrayList<Menu>();

		if (searchValue != null && !searchValue.trim().equals("")) {
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue);
		}

		menus = menuService.searchMenu(columnList, valueList);
		if (menus.isEmpty())
			menus = new ArrayList<Menu>();

		return menus;
	}

	// Column List
	public List<LabelValueBean> getColumnList() {
		List<LabelValueBean> columnList = new ArrayList<LabelValueBean>();
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.menuCode"), "code"));
		columnList.add(new LabelValueBean(ResourceHelper
				.getResources("label.menuName"), "name"));
		return columnList;
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
		return "/pages/setup/menuList";
	}

	public void prepareAdd() {
		// this.setCode(generateCode.generateMenuCode());
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
	public String prepareUpdate() {
		log.info("prepare for update menu...");
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("menuIdParam");
		log.info("ID==" + id);

		log.info("prepare for update menu end...");
		return null;
	}

	/**
	 * Edit menu data ke database untuk commandButton di halaman jsf
	 */
	public void updateMenu() {
		log.info("Update Menu Begin");

		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("custId");

		log.info("Update Menu End");

	}

	/**
	 * Delete
	 */
	public String deleteMenu() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("menuIdParam");

		return null;
	}

	/**
	 * Method for list model
	 * 
	 * @return
	 */
	public MenuDataModel getModel() {
		menuDataModel = new MenuDataModel(getMenuList());
		return menuDataModel;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(String systemMenu) {
		this.systemMenu = systemMenu;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
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

	public Menu[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(Menu[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

}
