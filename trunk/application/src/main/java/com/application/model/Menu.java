package com.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="menu2")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6816218359469190731L;

	@Id
	@Column(name = "menu_id")
	@SequenceGenerator(name = "my_seq", sequenceName = "menu_id_seq")
	@GeneratedValue(generator = "my_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "menu_code")
	private String menuCode;
	
	@Column(name = "parent_code")
	private String parentCode;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_url")
	private String menuUrl;
	
	@Column(name = "menu_type")
	private Integer menuType;
	
	@Column(name = "system_menu")
	private Integer systemMenu;
	
	@Column(name = "menu_level")
	private Integer menuLevel;
	
	@Column(name = "sequence")
	private Integer sequence;


	public Menu() {
	}

	public Menu(String menuName, String menuUrl, Integer menuType) {
		super();
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuType = menuType;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(Integer systemMenu) {
		this.systemMenu = systemMenu;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}


}