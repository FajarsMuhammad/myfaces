package com.application.dao;

import java.util.List;

import com.application.model.Menu2;

public interface Menu2Dao {

	public List<Menu2> searchMenu();
	
	public List<Menu2> searchMenu(List<Object> columnList, List<Object> valueList);
	
	public List<Menu2> getMenuByParent(String parent);
	
	public List<Menu2> getMenuByMenuCode(String menuCode);
	
	public List<Menu2> getMenuByUser(String user);

}