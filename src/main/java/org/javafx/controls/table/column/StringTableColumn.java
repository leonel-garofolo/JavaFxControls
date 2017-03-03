package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.StringCellFactory;

import javafx.scene.control.TableColumn;

public class StringTableColumn<S, T> extends TableColumn<S, T> {
	
	public StringTableColumn(){
		setCellFactory(new StringCellFactory<S, T>());
	}
}
