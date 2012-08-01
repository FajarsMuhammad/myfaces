package com.application.dao;

import java.util.List;

import com.application.model.User;

public interface UserDao extends BasisDao<User, Long> {
	
	public User getUserByLoginname(final String userName);
	
	public User getUserByLoginnamePassword(final String userName, final String password);

	public List<User> getUsers(final String userName);
}
