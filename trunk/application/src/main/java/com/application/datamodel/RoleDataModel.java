package com.application.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.application.model.Role;

public class RoleDataModel extends ListDataModel<Role> implements SelectableDataModel<Role>, Serializable {  
    
    private static final long serialVersionUID = -7044816495566358492L;
    public RoleDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleDataModel(List<Role> data) {  
        super(data);  
    } 

	@Override
	public Role getRowData(String rowKey) {
		List<Role> roles = (List<Role>) getWrappedData();  
          
        for(Role role : roles) {  
            if(String.valueOf(role.getId()).equals(rowKey))  
                return role;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Role role) {
		// TODO Auto-generated method stub
		return role.getId();
	}
  
   
}  
  
