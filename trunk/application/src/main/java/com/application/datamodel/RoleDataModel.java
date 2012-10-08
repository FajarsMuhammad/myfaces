package com.application.datamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.application.model.Role;

public class RoleDataModel extends LazyDataModel<Role> {  
    
    private static final long serialVersionUID = -7044816495566358492L;
	private List<Role> datasource;  
      
    public RoleDataModel(List<Role> datasource) {  
        this.datasource = datasource;  
    }  
      
    @Override  
    public Role getRowData(String rowKey) {  
        for(Role role : datasource) {  
            if(role.getRoleShortDescription().equals(rowKey))  
                return role;  
        }  
  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Role role) {  
        return role.getRoleShortDescription();  
    }  
  
    @Override  
    public List<Role> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
        List<Role> data = new ArrayList<Role>();  
  
        //filter  
        for(Role role : datasource) {  
            boolean match = true;  
  
            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
                try {  
                    String filterProperty = it.next();  
                    String filterValue = filters.get(filterProperty);  
                    String fieldValue = String.valueOf(role.getClass().getField(filterProperty).get(role));  
  
                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
                        match = true;  
                    }  
                    else {  
                        match = false;  
                        break;  
                    }  
                } catch(Exception e) {  
                    match = false;  
                }   
            }  
  
            if(match) {  
                data.add(role);  
            }  
        }  
  
        //sort  
		// if(sortField != null) {
		// Collections.sort(data, new LazySorter(sortField, sortOrder));
		// }
  
        //rowCount  
        int dataSize = data.size();  
        this.setRowCount(dataSize);  
  
        //paginate  
        if(dataSize > pageSize) {  
            try {  
                return data.subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return data.subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return data;  
        }  
    }  
}  
  
