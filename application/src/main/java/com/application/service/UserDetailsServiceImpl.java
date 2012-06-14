package com.application.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.application.model.User;

/*
 * Spring-security requires an implementation of UserDetailService. 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username){
		
		org.springframework.security.core.userdetails.User user = null;
		try {
		User dbUser = userService.getUserByLoginname(username);		
		user =  new org.springframework.security.core.userdetails.User(
				dbUser.getUserName(), 
				dbUser.getPassword().toLowerCase(),
				true,
				true,
				true,
				true,
				getAuthorities(dbUser.getAccess()));
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error in retrieving user, UserName not found");
		}
		
		return user;
	}

	
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		
		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		System.out.println("Grant ROLE_USER to this user");
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		// Check if this user has admin access 
		// Integer(1) as an admin user
		if ( access.compareTo(1) == 0) {
			// User has admin access
			//logger.debug("Grant ROLE_ADMIN to this user");
			System.out.println("Grant ROLE_ADMIN to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}

		// Return list of granted authorities
		return authList;
  }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
