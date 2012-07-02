package com.application.service;

import java.util.List;

import com.application.model.User;

public interface UserService {

	public User getUserByLoginname(final String userName);
	
	public User getUserByLoginnamePassword(final String userName, final String password);
	
	public List<User> getUsers(final String userName);
}
