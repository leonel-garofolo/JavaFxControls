package org.javafx.controls.table.column;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.column.cell.NumberCellFactory;

public class NumberTableColumn<S extends ModelTableViewFx<S, ?>, T> extends TableColumnCustom<S, T> {	
	public NumberTableColumn(){
		setCellFactory(new NumberCellFactory<S, T>());
	}
}
