package org.javafx.controls.table.column;

import javafx.scene.control.TableColumn;

public class TableColumnCustom <S, T> extends TableColumn<S, T> {
	protected boolean required;
	
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		if(required){			
			setStyle("-fx-font-style: italic;");
		}
		this.required = required;
	}
}
