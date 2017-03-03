package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.DateCellFactory;

import javafx.scene.control.TableColumn;

public class DateTableColumn<S, T> extends TableColumn<S, T> {
	
	public DateTableColumn(){
		setCellFactory(new DateCellFactory<S, T>());
	}
}
