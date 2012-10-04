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
import com.application.model.Menu2;
import com.application.service.Menu2Service;
import com.application.utility.LabelValueBean;
import com.application.utility.ResourceHelper;

@ManagedBean(name = "menuBean")
@RequestScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = -9070049982893688763L;

	private static final Logger log = Logger.getLogger(MenuBean.class);

	@ManagedProperty(value = "#{menu2Service}")
	private Menu2Service menu2Service;

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

	private LazyDataModel<Menu2> lazyModel;
	private MenuDataModel menuDataModel;
	private Menu2[] selectedMenus;

	/**
	 * get all menu data from database
	 */
	public List<Menu2> getMenu2List() {
		List<Object> columnList = null;
		List<Object> valueList = null;
		List<Menu2> menu2s = new ArrayList<Menu2>();

		if (searchValue != null && !searchValue.trim().equals("")) {
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue);
		}

		menu2s = menu2Service.searchMenu(columnList, valueList);
		if (menu2s.isEmpty())
			menu2s = new ArrayList<Menu2>();

		return menu2s;
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
		return "/pages/setup/menuList";
	}

	public void initialAdd() {
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
	public String initialUpdate() {
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
		menuDataModel = new MenuDataModel(getMenu2List());
		return menuDataModel;
	}

	public Menu2Service getMenu2Service() {
		return menu2Service;
	}

	public void setMenu2Service(Menu2Service menu2Service) {
		this.menu2Service = menu2Service;
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

	public Menu2[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(Menu2[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

}
