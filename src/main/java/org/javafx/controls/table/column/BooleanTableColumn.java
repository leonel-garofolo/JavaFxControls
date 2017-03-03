package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.BooleanCellFactory;

import javafx.scene.control.TableColumn;

public class BooleanTableColumn<S, T> extends TableColumn<S, T> {
	
	public BooleanTableColumn(){
		setCellFactory(new BooleanCellFactory<S, T>());
	}
}
