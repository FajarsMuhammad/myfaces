package com.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer{
	
	@Id
	@Column(name="customer_id")
	@SequenceGenerator(name="my_seq", sequenceName="customer_id_seq")
	@GeneratedValue(generator = "my_seq", strategy = GenerationType.SEQUENCE)	
	private Long id;
	
	@Column(name="customer_code")
	private String code;
	
	@Column(name="customer_name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="customer_grade")
	private String grade;
	
	@Column(name="term_of_payment")
	private boolean termOfPayment;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isTermOfPayment() {
		return termOfPayment;
	}
	public void setTermOfPayment(boolean termOfPayment) {
		this.termOfPayment = termOfPayment;
	}

	
	
	
}