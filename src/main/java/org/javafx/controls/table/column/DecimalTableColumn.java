package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.DecimalCellFactory;

import javafx.scene.control.TableColumn;

public class DecimalTableColumn<S, T> extends TableColumn<S, T> {
	
	public DecimalTableColumn(){
		setCellFactory(new DecimalCellFactory<S, T>());
	}
}
