package com.application.model;

import java.io.Serializable;

public class Menu implements Serializable {

        /**
	 * 
	 */
	private static final long serialVersionUID = 6816218359469190731L;

		private long id;
		private String menuCode;
		private String parentCode;
		private String menuName;
        private String menuUrl;
        private Integer menuType;
        private Integer systemMenu;
        private Integer menuLevel;    
        private Integer sequence;
        
        private Menu parent;

		public Menu(){}
		
		

		public Menu(String menuName, String menuUrl, Integer menuType) {
			super();
			this.menuName = menuName;
			this.menuUrl = menuUrl;
			this.menuType = menuType;
		}


		public long getId() {
			return id;
		}

		public void setId(long id) {
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

		public Menu getParent() {
			return parent;
		}

		public void setParent(Menu parent) {
			this.parent = parent;
		}
        
       
}