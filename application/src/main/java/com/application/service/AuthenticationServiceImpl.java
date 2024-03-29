package com.application.service;

import java.io.Serializable;

import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements com.application.service.AuthenticationService, Serializable {


	private static final long serialVersionUID = -3700924809209752942L;
	
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security

	@Override
	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);				
				return true;
			}
		} catch (AuthenticationException e) {			
		}
		return false;
	}

	/*@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//currentUser.unauthenticate();
	}
*/
	
}
