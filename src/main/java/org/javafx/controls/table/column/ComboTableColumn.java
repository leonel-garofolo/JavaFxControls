package org.javafx.controls.table.column;

import java.util.List;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.column.cell.ComboCellFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ComboTableColumn<S extends ModelTableViewFx<S, ?>, T> extends TableColumnCustom<S, T> {
	private ComboCellFactory<S,T> comboCellFactory;
	public ComboTableColumn(){
		this.comboCellFactory = new ComboCellFactory<S, T>();
		setCellFactory(comboCellFactory);		
	}
	
	public void loadData(ObservableList<T> data){
		comboCellFactory.loadData(data);
	}
	
	public void loadData(List<T> data){
		comboCellFactory.loadData(FXCollections.observableArrayList(data));		
	}
	
	public void addAction(EventHandler<ActionEvent> event){
		comboCellFactory.addAction(event);		
	}
	
	public void refresh(){
		comboCellFactory.getComboBoxEditingCell().reload();
	}
}
