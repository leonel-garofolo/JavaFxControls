package org.javafx.controls.table.column.cell.field;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CheckBoxCellField extends CheckBox {
	
	public CheckBoxCellField(){
		super();
		initComponents();
	}	
	
	private void initComponents() {		     
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {				
				if(event.getCode() == KeyCode.ENTER) {
					
				}
			}
		});
	}	
}
