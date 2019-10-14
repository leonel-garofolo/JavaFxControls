package org.javafx.controls.customs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.javafx.utils.DateUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DateTimePickerFieldVBox extends GridCustom{
	
	private String promptText = "dd/MM/yyyy HH:mm";
	private SimpleDateFormat formatDate = new SimpleDateFormat(promptText);
	private final DateTimePickerField field = new DateTimePickerField();
	private char typePosition;
	
	public DateTimePickerFieldVBox() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		//this.field.setPromptText(promptText);		
		addRow(1, lbl);
		addRow(2, field);		
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.field.setPromptText(promptText);
		this.formatDate = new SimpleDateFormat(promptText);
	}	

	public DateTimePickerField getField() {
		return field;
	}
	
	public void setValue(String value) throws ParseException {	
		if(value != null && !value.equals("")){			
			getField().setValue(Instant.ofEpochMilli(formatDate.parse(value).getTime()).atZone(ZoneId.systemDefault()).toLocalDate());					
		}else{
			getField().getEditor().clear();
		}
	}
	
	public void clear(){
		getField().getEditor().clear();
	}

	public void setValue(LocalDate value) {
		this.field.setValue(value);		
	}
	
	public void setValue(Date value) {	
		if(value != null){				
			getField().setDateTimeValue(Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());			
		}			
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}

	
	public Date getValue() {
		if(this.field.getValue() != null){
			return DateUtils.asDate(this.field.getDateTimeValue());		
		}
		return null;
	}
	
	public LocalDate getLocalDate(){
		return this.field.getValue();
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
