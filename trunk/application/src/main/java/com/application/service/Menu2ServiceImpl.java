package com.application.service;

import java.io.Serializable;
import java.util.List;

import com.application.dao.Menu2Dao;
import com.application.model.Menu2;

public class Menu2ServiceImpl implements Menu2Service, Serializable {
	
	private static final long serialVersionUID = -2008597716411567038L;
	private Menu2Dao menu2Dao;


	public Menu2Dao getMenu2Dao() {
		return menu2Dao;
	}

	public void setMenu2Dao(Menu2Dao menu2Dao) {
		this.menu2Dao = menu2Dao;
	}

	@Override
	public List<Menu2> searchMenu() {
		return menu2Dao.searchMenu();
	}
	
	public List<Menu2> getMenuByParent(String parent){
		return menu2Dao.getMenuByParent(parent);
	}
	
	public List<Menu2> getMenuByMenuCode(String menuCode){
		return menu2Dao.getMenuByMenuCode(menuCode);
	}
	
	public List<Menu2> getMenuByParentAndUser(String parentCode, String user){
		return menu2Dao.getMenuByParentAndUser(parentCode, user);
	}

}
