package org.javafx.controls.table.column;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.column.cell.DateCellFactory;

import javafx.event.EventHandler;

public class DateTableColumn<S extends ModelTableViewFx<S, ?>, T> extends TableColumnCustom<S, T> {
	
	public DateTableColumn(){
		setCellFactory(new DateCellFactory<S, T>());
	}
}
