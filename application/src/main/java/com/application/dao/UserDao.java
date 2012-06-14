package com.application.dao;

import com.application.model.User;

public interface UserDao {
	
	public User getUserByLoginname(final String userName);
	
	public User getUserByLoginnamePassword(final String userName, final String password);

}
