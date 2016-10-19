package org.javafx.controls.customs;

import javafx.scene.control.TextField;

public class NumberField extends TextField {

	private int maxValue;
	
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
	
	public int getValue() {		
		return Integer.valueOf(this.getText());
	}

	public void setValue(int value) {
		this.setText(String.valueOf(value));
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
