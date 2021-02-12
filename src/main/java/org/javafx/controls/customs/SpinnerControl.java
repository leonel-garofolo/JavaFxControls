package org.javafx.controls.customs;


import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;

public class SpinnerControl<T> extends Spinner<T>{
private int maxValue = 9;
	
	public SpinnerControl() {
		super();
		initComponents();
	}
	
	@SuppressWarnings({ "restriction" })
	private void initComponents() {	
		getEditor().setOnAction((ActionEvent e) -> {	
		});		
	}	
}
