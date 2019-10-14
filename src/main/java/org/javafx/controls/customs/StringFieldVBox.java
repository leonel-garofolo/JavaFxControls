package org.javafx.controls.customs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

public class StringFieldVBox extends GridCustom implements Initializable{
	public static char COMPONENT_HORIZONTAL='H';
	public static char COMPONENT_VERTICAL='V';
	
	private int maxValue = 255;
	private String text = "";
	private String promptText = "";
	private StringField field;
	private char typePosition;
	
	public StringFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {			
		this.field = new StringField();	
		field.setMaxValue(maxValue);
		field.setText(text);
		field.setPromptText(promptText);
		addRow(1, lbl);
		addRow(2, field);		
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getText() {
		return field.getText();
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
	
	public String getValue() {	
		if(field.getText() != null && field.getText().equals("")){
			return null;
		}
		return String.valueOf(field.getText());
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
	
	public void addAction(EventHandler<ActionEvent> event){		
		this.field.addEventHandler(ActionEvent.ACTION, event);
	}
	
	@Override
	public void requestFocus() {
		super.requestFocus();
		this.field.requestFocus();
	}
	
	public char getTypePosition() {
		return typePosition;
	}

	public void setTypePosition(char typePosition) {
		this.typePosition = typePosition;
		getChildren().clear();
		if(typePosition == 'H'){
			addColumn(1, lbl);
			addColumn(2, field);	
		}
		if(typePosition == 'V'){
			addRow(1, lbl);
			addRow(2, field);	
		}
	}
}
