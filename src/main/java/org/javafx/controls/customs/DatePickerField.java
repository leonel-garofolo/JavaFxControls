package org.javafx.controls.customs;

import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DatePickerField extends DatePicker {
	
	public DatePickerField(){
		super();
		initComponents();
	}	
	
	@SuppressWarnings("restriction")
	private void initComponents() {		     
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {				
				if(event.getCode() == KeyCode.ENTER) {					
					setValue(LocalDate.now());
				}
			}
		});
	}	
}
