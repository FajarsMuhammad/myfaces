package com.application.service;

import com.application.dao.UserDao;
import com.application.model.User;

public class UserServiceImpl implements UserService {
	
	UserDao userDao;

	@Override
	public User getUserByLoginname(String userName) {
		return getUserDao().getUserByLoginname(userName);
	}
	
	@Override
	public User getUserByLoginnamePassword(String userName, String password) {
		return getUserDao().getUserByLoginnamePassword(userName, password);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	

}
