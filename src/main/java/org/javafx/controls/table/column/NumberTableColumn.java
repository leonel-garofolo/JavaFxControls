package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.NumberCellFactory;

import javafx.scene.control.TableColumn;

public class NumberTableColumn<S, T> extends TableColumn<S, T> {
	
	public NumberTableColumn(){
		setCellFactory(new NumberCellFactory<S, T>());
	}
}
