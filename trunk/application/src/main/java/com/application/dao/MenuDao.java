package com.application.dao;

import java.util.List;

import com.application.model.Menu;

public interface MenuDao extends BasisDao<Menu, Long> {

	public List<Menu> searchMenu();
	
	public List<Menu> searchMenu(List<Object> columnList, List<Object> valueList);
	
	public List<Menu> getMenuByParent(String parent);
	
	public List<Menu> getMenuByMenuCode(String menuCode);
	
	public List<Menu> getMenuByUser(String user);
	
	public Menu getById(long id);
	
	public Menu getByCode(String code);
	
	public void initialize(Menu menu);


}