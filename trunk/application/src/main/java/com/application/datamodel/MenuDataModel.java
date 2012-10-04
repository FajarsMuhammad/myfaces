package com.application.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.application.model.Menu2;

public class MenuDataModel extends ListDataModel<Menu2> implements SelectableDataModel<Menu2> {

	public MenuDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuDataModel(List<Menu2> data) {  
        super(data);  
    } 

	@Override
	public Menu2 getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        
        List<Menu2> menus = (List<Menu2>) getWrappedData();  
          
        for(Menu2 menu : menus) {  
            if(menu.getMenuCode().equals(rowKey))  
                return menu;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Menu2 menu) {
		// TODO Auto-generated method stub
		return menu.getMenuCode();
	}

}
