package org.javafx.controls.customs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class StringFieldVBox extends VBoxCustom implements Initializable{
	public static char COMPONENT_HORIZONTAL='H';
	public static char COMPONENT_VERTICAL='V';
	
	private int maxValue = 255;
	private String text = "";
	private String promptText = "";
	private StringField field;
	
	public StringFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {			
		this.field = new StringField();	
		field.setMaxValue(maxValue);
		field.setText(text);
		field.setPromptText(promptText);
		getChildren().addAll(lbl,field);		
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.field.setText(text);
	}

	public StringField getField() {
		return field;
	}

	public void setField(StringField field) {
		this.field = field;
	}
	
	public int getValue() {		
		return Integer.valueOf(field.getText());
	}

	public void setValue(int value) {
		field.setText(String.valueOf(value));
	}
	
	public void setValue(String value) {		
		field.setText(value);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("asda");
		field.setId(this.getId() + "Field");
		
	}		
}
