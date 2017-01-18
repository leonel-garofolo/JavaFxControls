package org.javafx.controls.customs;

public class NumberFieldVBox extends VBoxCustom{
	public static char COMPONENT_HORIZONTAL='H';
	public static char COMPONENT_VERTICAL='V';
	
	private int maxValue = 255;
	private String text = "";
	private String promptText = "";
	private NumberField field;
	
	public NumberFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new NumberField();
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

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.field.setPromptText(promptText);
	}

	public NumberField getField() {
		return field;
	}

	public void setField(NumberField field) {
		this.field = field;
	}
	
	public Integer getValue() {		
		return field.getValue();
	}

	public void setValue(int value) {
		field.setValue(value);
	}
	
	public void setValue(String value) {		
		field.setValue(value);
	}
}
