package com.application.model;

import java.util.Date;

public class User {
	
	private Long id;
	private String userName;
	private String password;
	public Date createdDate;
	private String usrLastname;
	private String usrFirstname;
	private String usrEmail;
	private String usrLocale;
	private boolean usrEnabled = true;
	private boolean usrAccountnonexpired = true;
	private boolean usrCredentialsnonexpired = true;
	private boolean usrAccountnonlocked = true;
	private String usrToken;
	private Integer access;
	
	public User(){}
	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsrLastname() {
		return usrLastname;
	}

	public void setUsrLastname(String usrLastname) {
		this.usrLastname = usrLastname;
	}

	public String getUsrFirstname() {
		return usrFirstname;
	}

	public void setUsrFirstname(String usrFirstname) {
		this.usrFirstname = usrFirstname;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrLocale() {
		return usrLocale;
	}

	public void setUsrLocale(String usrLocale) {
		this.usrLocale = usrLocale;
	}

	public boolean isUsrEnabled() {
		return usrEnabled;
	}

	public void setUsrEnabled(boolean usrEnabled) {
		this.usrEnabled = usrEnabled;
	}

	public boolean isUsrAccountnonexpired() {
		return usrAccountnonexpired;
	}

	public void setUsrAccountnonexpired(boolean usrAccountnonexpired) {
		this.usrAccountnonexpired = usrAccountnonexpired;
	}

	public boolean isUsrCredentialsnonexpired() {
		return usrCredentialsnonexpired;
	}

	public void setUsrCredentialsnonexpired(boolean usrCredentialsnonexpired) {
		this.usrCredentialsnonexpired = usrCredentialsnonexpired;
	}

	public boolean isUsrAccountnonlocked() {
		return usrAccountnonlocked;
	}

	public void setUsrAccountnonlocked(boolean usrAccountnonlocked) {
		this.usrAccountnonlocked = usrAccountnonlocked;
	}

	public String getUsrToken() {
		return usrToken;
	}

	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}
	
	

}
