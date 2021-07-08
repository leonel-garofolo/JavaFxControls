package org.javafx.controls.customs;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class StringField extends TextField {
	private int maxValue = 255;	
	
	public StringField() {
		super();
		initComponents();   
	}
	
	@SuppressWarnings({ "restriction" })
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

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public String getValue() {		
		return this.getText();
	}

	public void setValue(int value) {
		this.setText(String.valueOf(value));
	}
	
	public void setValue(String value) {	
		if(value != null){
			this.setText(value);
		}
	}
}
