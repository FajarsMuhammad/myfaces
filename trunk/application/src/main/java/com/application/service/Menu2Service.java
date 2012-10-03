package com.application.service;

import java.util.List;

import com.application.model.Menu2;

public interface Menu2Service {

	List<Menu2> searchMenu();
	
	public List<Menu2> getMenuByParent(String parent);

}