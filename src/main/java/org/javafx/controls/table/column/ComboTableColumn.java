package org.javafx.controls.table.column;

import org.javafx.controls.table.column.cell.ComboCellFactory;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class ComboTableColumn<S, T> extends TableColumn<S, T> {
	private ComboCellFactory<S,T> comboCellFactory;
	public ComboTableColumn(){
		this.comboCellFactory = new ComboCellFactory<S, T>();
		setCellFactory(comboCellFactory);
	}
	
	public void loadData(ObservableList<T> data){
		comboCellFactory.loadData(data);
	}
}
