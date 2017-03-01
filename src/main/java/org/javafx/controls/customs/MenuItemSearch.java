package org.javafx.controls.customs;

import javafx.scene.control.MenuItem;

public class MenuItemSearch extends MenuItem{
	
	public MenuItemSearch(){
		super();
	}
	
	public MenuItemSearch(String nodeValue) {
		super(nodeValue);
	}

	@Override 
	 public String toString() {
	        return this.getText();
	 }
}
