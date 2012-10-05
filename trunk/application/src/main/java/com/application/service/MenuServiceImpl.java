package com.application.service;

import java.io.Serializable;
import java.util.List;

import com.application.dao.MenuDao;
import com.application.model.Customer;
import com.application.model.Menu;

public class MenuServiceImpl implements MenuService, Serializable {
	
	private static final long serialVersionUID = -2008597716411567038L;
	private MenuDao menuDao;


	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> searchMenu() {
		return menuDao.searchMenu();
	}
	
	public List<Menu> searchMenu(List<Object> columnList, List<Object> valueList){
		return menuDao.searchMenu(columnList, valueList);
	}
	
	public List<Menu> getMenuByParent(String parent){
		return menuDao.getMenuByParent(parent);
	}
	
	public List<Menu> getMenuByMenuCode(String menuCode){
		return menuDao.getMenuByMenuCode(menuCode);
	}
	
	public List<Menu> getMenuByUser(String user){
		return menuDao.getMenuByUser(user);
	}
	
	public Menu getById(long id){
		return menuDao.getById(id);
	}
	
	public Menu getByCode(String code){
		return menuDao.getByCode(code);
	}
	
	@Override
	public void update(Menu menu) {
		menuDao.update(menu);
	}

	@Override
	public void delete(Menu menu) {
		menuDao.delete(menu);
	}
	
	public void save(Menu customer) {
		menuDao.save(customer);
	}

	public List<Menu> findAll() {
		return menuDao.findAll();
	}

}
