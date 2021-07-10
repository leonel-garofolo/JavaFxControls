package org.javafx.form.model;

import java.util.ArrayList;
import java.util.List;

public class FormFieldList extends FormField{
	private List<Object> list;
	
	public FormFieldList(String id, String label, List<Object> list, Object value) {
		super();
		this.id = id;
		this.label = label;		
		this.value = value;
		this.type = FormFieldType.LIST;
		this.list = list;
	}

	public List<Object> getList() {
		if(list == null)
			list = new ArrayList<Object>();
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}	
}
