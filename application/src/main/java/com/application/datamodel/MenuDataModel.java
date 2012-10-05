package com.application.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.application.model.Menu;

public class MenuDataModel extends ListDataModel<Menu> implements SelectableDataModel<Menu> {

	public MenuDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuDataModel(List<Menu> data) {  
        super(data);  
    } 

	@Override
	public Menu getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        
        List<Menu> menus = (List<Menu>) getWrappedData();  
          
        for(Menu menu : menus) {  
            if(menu.getMenuCode().equals(rowKey))  
                return menu;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Menu menu) {
		// TODO Auto-generated method stub
		return menu.getMenuCode();
	}

}
