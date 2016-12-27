package org.javafx.controls.customs;

import javafx.collections.ObservableList;

public class ComboBoxAutoFieldVBox<T> extends VBoxCustom{
	
	private String promptText = "";	
	private ComboBoxAutoComplete<T> field;
	
	public ComboBoxAutoFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new ComboBoxAutoComplete<T>();
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

	public ComboBoxAutoComplete<T> getField() {
		return field;
	}

	public void setField(ComboBoxAutoComplete<T> field) {
		this.field = field;
	}

	public void setValue(T value) {
		this.field.setValue(value);		
	}
	
	public Object getValue() {
		return this.field.getValue();		
	}

	public void setItems(ObservableList<T> object) {
		this.field.setItems(object);
	}
	
	public ObservableList<T> getItems(){
		return this.field.getItems();
	}

	public void reload() {
		this.field.reload();
	}
}
