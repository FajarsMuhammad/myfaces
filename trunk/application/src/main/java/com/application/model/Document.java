package com.application.model;

import java.io.Serializable;

public class Document implements Serializable {

        /**
	 * 
	 */
	private static final long serialVersionUID = 6816218359469190731L;

		private String name;
        
        private String url;
        
        private String type;
        
        public Document(String name, String url, String type) {
                this.name = name;
                this.url = url;
                this.type = type;
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
                Document other = (Document) obj;
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