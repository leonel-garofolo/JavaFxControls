package org.javafx.controls.customs;

import java.io.IOException;
import java.math.BigDecimal;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class DecimalField extends TextField {
	
	private int maxValue;
	
	public DecimalField(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }       
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceSelection(text);
		}
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getValue() {		
		return new BigDecimal(this.getText());
	}

	public void setValue(BigDecimal value) {
		this.setText(value.toString());
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
