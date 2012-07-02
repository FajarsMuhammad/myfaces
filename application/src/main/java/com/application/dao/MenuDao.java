package com.application.dao;

import java.util.List;

import com.application.model.Menu;

public interface MenuDao {

	public List<Menu> searchMenu();
	
	public List<Menu> getMenuByParent(String parent);

}