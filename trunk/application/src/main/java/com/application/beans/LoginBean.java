package com.application.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.application.service.AuthenticationService;

public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginBean.class);
	
	private String userName;
	private String password;

	private AuthenticationService authenticationService;

	public String login() {

		boolean success = authenticationService.login(userName, password);
		if (success){
			return "success";
		}
		else{
			log.debug("notsucces");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login or password incorrect."));
			return "failed";
		}
	}
	
	//for action
//	public void logout() {
//		authenticationService.logout();
//		FacesContext fc = FacesContext.getCurrentInstance();
//		NavigationHandler nav = fc.getApplication().getNavigationHandler();
//		nav.handleNavigation(fc, null,
//				"/login?faces-redirect=true");
//		fc.renderResponse();
//	}
//	
//	public String doLogout() {
//		authenticationService.logout();
//		return "success";
//	}
	
	public void destroy() {
		//grantedAuthoritySet = null;
		SecurityContextHolder.clearContext();
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	

	
}
