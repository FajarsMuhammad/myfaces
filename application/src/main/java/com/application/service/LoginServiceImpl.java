/*package com.application.service;

import com.application.dao.LoginDao;
import com.application.model.User;


 * Spring-security requires an implementation of UserDetailService. 
 
public class LoginServiceImpl implements LoginService {
	
	LoginDao loginDao;

	//private HashMap<String, User> users = new HashMap<String, User>();
	
	public LoginDao getLoginDao() {
		return loginDao;
	}


	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}


	@Override
	public User loadUserByUsername(String userName){
		
		User user = loginDao.loadUserByUsername(userName);
		
		return user;
	}

	@PostConstruct
	public void init() {
		
		// sample roles		
		Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		
		Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		userAuthorities.add(new GrantedAuthorityImpl("ROLE_REGISTERED"));
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		// sample users with roles set
		users.put("admin", new User("admin", "admin"));
		
		users.put("user", new User("user", "user"));
	}
}
*/