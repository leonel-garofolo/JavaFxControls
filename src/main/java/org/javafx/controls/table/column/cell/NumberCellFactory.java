package org.javafx.controls.table.column.cell;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.TableViewEdit;
import org.javafx.controls.table.column.cell.field.NumberCellField;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class NumberCellFactory<S extends ModelTableViewFx<S, ?>, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new NumberCell();
	}
	
	class NumberCell extends TableCell<S, Integer> {

        private NumberCellField numberField;
        private NumberCell() {
        	super();
        	createTextField();
        }

        @Override
        public void startEdit() {       
            if (numberField == null) {
            	createTextField();
			}
        	if (!isEmpty()) {
				super.startEdit();							
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						numberField.requestFocus();
						numberField.selectAll();
					}
				});								
			}
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
        }

        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);          
            
            if (empty) {
            	if (numberField != null) {
            		numberField.setValue(item != null ? String.valueOf(item): null);
                }
            	setGraphic(null);
            } else {
            	if(isEditing()){
            		if (numberField != null) {
                		numberField.setValue(getInteger());
                    }
            	}               
                setGraphic(numberField);            	
            }
        }

        private void createTextField() {
            numberField = new NumberCellField();
            numberField.setValue(getInteger());
            numberField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            numberField.setOnAction((e) -> commitEdit(numberField.getValue()));
            numberField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(numberField.getValue());
                }
            });
            numberField.textProperty().addListener((observable, oldValue, newValue) -> {
				if(oldValue != newValue){
					((TableViewEdit<S>) getTableView()).editRow();
				}			   
			});
            
            numberField.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
						commitEdit(numberField.getValue());
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

        private Integer getInteger() {
            return getItem() != null ? getItem() : null;
        }
    }
}
