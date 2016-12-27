package org.javafx.controls.customs;



import java.time.LocalDate;

public class DatePickerFieldVBox extends VBoxCustom{
	
	private String promptText = "";	
	private DatePickerField field;
	
	public DatePickerFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new DatePickerField();
		field.setPromptText(promptText);
		getChildren().addAll(lbl,field);		
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.field.setPromptText(promptText);
	}	

	public DatePickerField getField() {
		return field;
	}

	public void setField(DatePickerField field) {
		this.field = field;
	}

	public void setValue(LocalDate value) {
		this.field.setValue(value);		
	}
	
	public LocalDate getValue() {
		return this.field.getValue();		
	}
}
