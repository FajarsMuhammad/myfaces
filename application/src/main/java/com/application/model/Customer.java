package com.application.model;

import java.util.Date;

public class Customer{
	
	private long customerId;
	private String code;
	private String name;
	private String address;
	private String gender;
	private Date createdDate;
	private String grade;
	private Integer termOfPayment;
	
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getTermOfPayment() {
		return termOfPayment;
	}
	public void setTermOfPayment(Integer termOfPayment) {
		this.termOfPayment = termOfPayment;
	}
	
	
	
}