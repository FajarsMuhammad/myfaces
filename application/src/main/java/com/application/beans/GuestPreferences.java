package com.application.beans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="guestPreferences")
@RequestScoped
public class GuestPreferences implements Serializable {  
	 private String theme = "start"; //default

     public String getTheme() {
             Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
             if(params.containsKey("theme")) {
                     theme = params.get("theme");
             }
            
             return theme;
     }

     public void setTheme(String theme) {
             this.theme = theme;
     }
}  