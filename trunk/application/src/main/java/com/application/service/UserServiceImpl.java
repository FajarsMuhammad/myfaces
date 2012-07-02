package com.application.service;

import java.util.List;

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
	
	public List<User> getUsers(final String userName){
		return getUserDao().getUsers(userName);
	}
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	

}
