package com.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.TreeNode;

public class Menu implements Serializable {

        /**
	 * 
	 */
	private static final long serialVersionUID = 6816218359469190731L;

		private long id;
	
		private String parent;
		
		private String name;
        
        private String url;
        
        private String type;
        
        private String kind;
         
        private List<Menu> menuFiles = new ArrayList<Menu>();
        
       
		public List<Menu> getMenuFiles() {
			return menuFiles;
		}

		public void setMenuFiles(List<Menu> menuFiles) {
			this.menuFiles = menuFiles;
		}

		public Menu(){}
        
        public Menu(String name, String url, String type) {
                this.name = name;
                this.url = url;
                this.type = type;
        }
        
        
        public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public String getParent() {
			return parent;
		}


		public void setParent(String parent) {
			this.parent = parent;
		}


		public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }
     

        public String getKind() {
			return kind;
		}


		public void setKind(String kind) {
			this.kind = kind;
		}


		//Eclipse Generated hashCode and equals
        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((name == null) ? 0 : name.hashCode());
                result = prime * result + ((url == null) ? 0 : url.hashCode());
                result = prime * result + ((type == null) ? 0 : type.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                Menu other = (Menu) obj;
                if (name == null) {
                        if (other.name != null)
                                return false;
                } else if (!name.equals(other.name))
                        return false;
                if (url == null) {
                        if (other.url != null)
                                return false;
                } else if (!url.equals(other.url))
                        return false;
                if (type == null) {
                        if (other.type != null)
                                return false;
                } else if (!type.equals(other.type))
                        return false;
                return true;
        }

        @Override
        public String toString() {
                return name;
        }
}