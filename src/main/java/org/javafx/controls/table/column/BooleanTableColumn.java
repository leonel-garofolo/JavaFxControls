package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.BooleanCellFactory;

public class BooleanTableColumn<S, T> extends TableColumnCustom<S, T> {
	public BooleanTableColumn(){
		setCellFactory(new BooleanCellFactory<S, T>());		
	}
}
