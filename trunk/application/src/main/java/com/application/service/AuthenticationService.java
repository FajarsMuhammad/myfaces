package com.application.service;

import javax.annotation.security.RolesAllowed;

public interface AuthenticationService {
	
	boolean login(String username, String password);

	@RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
	void logout();
}
