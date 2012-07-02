package com.application.service;

import java.io.Serializable;
import java.util.List;

import com.application.dao.MenuDao;
import com.application.model.Menu;

public class MenuServiceImpl implements MenuService, Serializable {
	
	private static final long serialVersionUID = -2008597716411567038L;
	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> searchMenu() {
		return menuDao.searchMenu();
	}
	
	public List<Menu> getMenuByParent(String parent){
		return menuDao.getMenuByParent(parent);
	}

}
