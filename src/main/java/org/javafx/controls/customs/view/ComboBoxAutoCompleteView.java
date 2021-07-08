package org.javafx.controls.customs.view;

import org.javafx.controls.customs.ComboBoxAutoComplete;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.VBox;

public class ComboBoxAutoCompleteView<T> extends VBox{
	
	private Label label;
	private ComboBoxAutoComplete<T> field;
	
	public ComboBoxAutoCompleteView(String label){
		this.label = new Label(label);
		this.field = new ComboBoxAutoComplete<T>();
		initView();
	}

	private void initView() {
		getChildren().add(label);
		getChildren().add(field);		
	}
	
	
	public ObservableList<T> getItems() {
		return field.getItems();
	}
	
	public SingleSelectionModel<T> getSelectionModel(){
		return field.getSelectionModel();
	}

	public void addItem(T item) {
		field.getItems().add(item);
		field.reload();		
	}
}
