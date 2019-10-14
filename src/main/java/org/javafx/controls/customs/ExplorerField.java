package org.javafx.controls.customs;

import com.lg.utilities.Explorer;

import javafx.collections.ObservableList;

public class ExplorerField<T extends Explorer> extends GridCustom {
	private String promptText = "";	
	private ExplorerControl<T> field;	
	private char typePosition;
	
	public ExplorerField() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new ExplorerControl<T>();
		field.setPromptText(promptText);		
		addRow(1, lbl);
		addRow(2, field);			
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
	
	public void addAll(ObservableList<T> items){
		this.field.addAll(items);
	}
}