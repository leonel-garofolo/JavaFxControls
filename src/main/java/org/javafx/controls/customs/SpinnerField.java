package org.javafx.controls.customs;

import java.util.List;

import org.javafx.controls.customs.GridCustom;
import org.javafx.controls.customs.SpinnerControl;

import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;

public class SpinnerField<T> extends GridCustom {
	private SpinnerControl<T> field;	
	private char typePosition;
	
	public SpinnerField() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new SpinnerControl<T>();			
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
	
	public final void setValueFactory(SpinnerValueFactory<T> value) {
		this.field.setValueFactory(value);
	}
	
	public T getValue(){
		return this.field.getValue();
	}
	
	public final void setRangeValue(T valueStart, T valueEnd, T initialValue){
		SpinnerValueFactory<T> valueFactory = null;
		if(valueStart instanceof Integer){
			valueFactory = (SpinnerValueFactory<T>) new SpinnerValueFactory.IntegerSpinnerValueFactory((Integer)valueStart, (Integer)valueEnd, (Integer)initialValue);
		}
		if(valueStart instanceof Double){
			valueFactory = (SpinnerValueFactory<T>) new SpinnerValueFactory.DoubleSpinnerValueFactory((Double)valueStart, (Double)valueEnd, (Double)initialValue);
		}		
		this.field.setValueFactory(valueFactory);
	}
	
	public final void setRangeValue(List<T> values){
		SpinnerValueFactory<T> valueFactory = 
				new SpinnerValueFactory.ListSpinnerValueFactory<T>(FXCollections.observableList(values));
		
		this.field.setValueFactory(valueFactory);
	}
}