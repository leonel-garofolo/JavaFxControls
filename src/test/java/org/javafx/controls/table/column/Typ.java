package org.javafx.controls.table.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Typ {

	private final SimpleStringProperty typ;

	public Typ(String typ) {
		this.typ = new SimpleStringProperty(typ);
	}

	public String getTyp() {
		return this.typ.get();
	}

	public StringProperty typProperty() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ.set(typ);
	}

	@Override
	public String toString() {
		return typ.get();
	}	
}