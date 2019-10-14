package org.javafx.controls.table.column;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.column.cell.DecimalCellFactory;

public class DecimalTableColumn<S extends ModelTableViewFx<S, ?>, T> extends TableColumnCustom<S, T> {
	
	public DecimalTableColumn(){
		setCellFactory(new DecimalCellFactory<S, T>());
	}
}
