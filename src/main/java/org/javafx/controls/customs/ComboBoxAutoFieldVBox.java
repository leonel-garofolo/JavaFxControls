package org.javafx.controls.customs;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ComboBoxAutoFieldVBox<T> extends GridCustom{
	
	private String promptText = "";	
	private AutoFillComboBox<T> field;	
	private char typePosition;
	
	public ComboBoxAutoFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new AutoFillComboBox<T>();
		field.setPromptText(promptText);		
		addRow(1, lbl);
		addRow(2, field);			
	}
	
	public AutoFillComboBox<T> getCombo(){
		return this.field;
	}		
	
	public void clearSelection(){
		if(!field.getSelectionModel().isEmpty()){
			field.getSelectionModel().clearSelection();		
		}		
	}
	
	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.field.setPromptText(promptText);
	}

	public AutoFillComboBox<T> getField() {
		return field;
	}

	public void setField(AutoFillComboBox<T> field) {
		this.field = field;
	}

	public void setValue(T value) {
		this.field.setValue(value);
	}
	
	public T getValue() {
		return this.field.getValue();		
	}

	public void setItems(ObservableList<T> object) {
		this.field.setRecords(object);
	}
	
	public ObservableList<T> getItems(){
		return this.field.getItems();
	}
	
	public void add(T item){
		if(this.field.getItems() != null){
			this.field.addRecords(item);			
		}
	}
	
	public void addAll(List<T> items){
		if(this.field.getItems() != null){
			this.field.setRecords(items);			
		}
	}
		
	public EventHandler<ActionEvent> getOnActionField() {
		return this.field.getOnAction();
	}

	public void setOnActionField(EventHandler<ActionEvent> onAction) {		
		this.field.addEventHandler(ActionEvent.ACTION, onAction);
	}

	@Override
	public void requestFocus() {
		super.requestFocus();
		this.field.requestFocus();
	}
	
	
	public void clear(){
		if(this.field.getSelectionModel() != null)
			this.field.getSelectionModel().clearSelection();
		if(this.field.getItems() != null && this.field.getItems().size() > 0)
			this.field.getItems().clear();		
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
