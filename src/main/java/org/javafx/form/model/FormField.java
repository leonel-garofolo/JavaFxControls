package org.javafx.form.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FormField {
	protected String id;
	protected String label;
	protected FormFieldType type ;
	protected Object value;
	protected boolean required;
	protected String hint;
	protected EventHandler<ActionEvent> action;

	
	public FormField() {
		super();
	}
	
	public FormField(FormFieldType type) {
		super();
		this.type = type;		
	}
	
	public FormField(String label, FormFieldType type) {
		super();
		this.label = label;
		this.type = type;
	}
	
	public FormField(String id, String label, Object value) {
		super();
		this.id = id;
		this.label = label;		
		this.value = value;
	}	
	
	public FormField(String id, String label, Object value, boolean isRequired, String hint) {
		this(id, label, value);
		this.required = isRequired;
		this.hint = hint;
	}
	
	public FormField(String id, String label, FormFieldType type, Object value) {
		super();
		this.id = id;
		this.label = label;		
		this.type = type;
		this.value = value;
	}		
	
	public FormField(String id, String label, FormFieldType type, String value, boolean isRequired) {
		this(id, label, type, value);
		this.required= isRequired;
	}
	
	public FormField(String id, String label, FormFieldType type, String value, boolean isRequired, String hint) {
		this(id, label, type, value);
		this.required= isRequired;
		this.hint = hint;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public FormFieldType getType() {
		return type;
	}

	public void setType(FormFieldType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public void setAction(EventHandler<ActionEvent> action) {
		this.action = action;
	}

	public EventHandler<ActionEvent> getAction() {
		return this.action;
	}
}
