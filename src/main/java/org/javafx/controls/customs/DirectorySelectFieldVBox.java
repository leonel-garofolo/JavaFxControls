package org.javafx.controls.customs;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

public class DirectorySelectFieldVBox extends GridCustom implements EventHandler<ActionEvent>{
	public static char COMPONENT_HORIZONTAL='H';
	public static char COMPONENT_VERTICAL='V';
	
	private int maxValue = 255;
	private String text = "";
	private String promptText = "";
	private StringField field;
	private Button button;
	private char typePosition;
	
	public DirectorySelectFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new StringField();
		this.button = new Button();
		button.setOnAction(this);
		field.setMaxValue(maxValue);
		field.setText(text);
		field.setPromptText(promptText);
		field.setDisable(true);
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(field, button);
		addRow(1, lbl);
		addRow(2, hBox);
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

	public StringField getField() {
		return field;
	}

	public void setField(StringField field) {
		this.field = field;
	}
	
	public String getValue() {		
		return field.getText();
	}

	public void setValue(String value) {		
		field.setValue(value);
	}

	@Override
	public void handle(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();
		if(!field.getText().equals("")) {
			chooser.setInitialDirectory(new File(field.getText()));	
		}			
		File file =chooser.showDialog(null);
		if(file != null){
			field.setText(file.getPath());
		}		
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