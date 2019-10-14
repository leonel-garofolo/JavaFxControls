package org.javafx.controls.table.column;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.column.cell.StringCellFactory;

public class StringTableColumn<S extends ModelTableViewFx<S, ?>, T> extends TableColumnCustom<S, T> {

	public StringTableColumn(){
		setCellFactory(new StringCellFactory<S, T>());
	}	
}
