package org.javafx.controls.table.column.cell.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class NumberCellField extends TextField {
	private int maxValue = 9;
	
	public NumberCellField() {
		super();
		initComponents();
	}
	
	//TODO crear parametro de numero de secuencia de foco
	private void initComponents() {
		textProperty().addListener(new ChangeListener<String>() {

            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (ignore || s1 == null)
                    return;
                if (s1.length() > maxValue) {
                    ignore = true;
                    setText(s1.substring(0, maxValue));
                    ignore = false;
                }
            }
        });		
		this.setOnAction((ActionEvent e) -> {	
						
		});	
	}	
	
	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9]*")) {
			super.replaceText(start, end, text);
		}				
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9]*")) {
			super.replaceSelection(text);
		}		
	}
	
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public Integer getValue() {		
		if(this.getText() != null && this.getText().equals("")){
			return null;
		}
		try{
			return Integer.valueOf(this.getText());
		}catch (NullPointerException e) {
			return null;
		}
	}

	public void setValue(Integer value) {
		if(value != null){
			this.setText(String.valueOf(value));
		}		
	}
	
	public void setValue(String value) {	
		if(value != null){
			this.setText(value);
		}
	}
}
