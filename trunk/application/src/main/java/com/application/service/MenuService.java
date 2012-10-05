package com.application.service;

import java.util.List;

import com.application.model.Menu;

public interface MenuService {

	List<Menu> searchMenu();
	
	public List<Menu> searchMenu(List<Object> columnList, List<Object> valueList);
	
	public List<Menu> getMenuByParent(String parent);
	
	public List<Menu> getMenuByMenuCode(String menuCode);
	
	public List<Menu> getMenuByUser(String user);
	
	public Menu getById(long id);
	
	public Menu getByCode(String code);

	public void save(Menu menu);

	public List<Menu> findAll();

	public void update(Menu menu);

	public void delete(Menu menu);
	
	

}