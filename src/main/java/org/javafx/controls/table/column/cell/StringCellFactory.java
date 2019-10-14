package org.javafx.controls.table.column.cell;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.TableViewEdit;
import org.javafx.controls.table.column.cell.field.StringCellField;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class StringCellFactory<S extends ModelTableViewFx<S, ?>, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new StringCell();
	}

	class StringCell extends TableCell<S, String> {
		private StringCellField stringField;

		private StringCell() {
			super();
			createTextField();
		}

		@Override
		public void startEdit() {
			if (stringField == null) {
            	createTextField();
			}
        	if (!isEmpty()) {
				super.startEdit();							
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						stringField.requestFocus();
						stringField.selectAll();
					}
				});								
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();			
		}

		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
            	stringField.setValue(item);
            	setGraphic(stringField);
            }					
		}

		private void createTextField() {
			stringField = new StringCellField();
			stringField.setValue(getString());
			stringField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);	
							
			stringField.setOnAction((e) -> {
				commitEdit(stringField.getValue());	
				if(getTableRow() != null && isEditing()){
					((TableViewEdit<S>) getTableView()).getSelectionModel().select(getTableRow().getIndex());
					((TableViewEdit<S>) getTableView()).editRow();
				}
			});
			
			stringField.textProperty().addListener((observable, oldValue, newValue) -> {
				if(oldValue != newValue){
					((TableViewEdit<S>) getTableView()).editRow();
				}
			});	
								
			stringField.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
						commitEdit(stringField.getValue());
						TableColumn nextColumn = ((TableViewEdit<S>) getTableView()).getNextColumn(!t.isShiftDown(), getTableColumn());
						if (nextColumn != null) {
							getTableView().edit(getTableRow().getIndex(), nextColumn);
						}
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});			
		}		

		private String getString() {
			return getItem() == null ? "" : getItem();
		}
	}
}
