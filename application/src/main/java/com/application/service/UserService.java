package com.application.service;

import com.application.model.User;

public interface UserService {

	public User getUserByLoginname(final String userName);
	
	public User getUserByLoginnamePassword(final String userName, final String password);
}
