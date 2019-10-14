package org.javafx.controls.table.column.cell;

import java.math.BigDecimal;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.TableViewEdit;
import org.javafx.controls.table.column.cell.field.DecimalCellField;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class DecimalCellFactory<S extends ModelTableViewFx<S, ?>, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new DecimalCell();
	}
	
	class DecimalCell extends TableCell<S, BigDecimal> {
        private DecimalCellField decimalField;
        
        private DecimalCell() {
        	super();
        	createTextField();
        }

        @Override
        public void startEdit() {        	
            if (decimalField == null) {
            	createTextField();
			}
        	if (!isEmpty()) {
				super.startEdit();							
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						decimalField.requestFocus();
						decimalField.selectAll();
					}
				});								
			}
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
        }

        @Override
        public void updateItem(BigDecimal item, boolean empty) {            
            super.updateItem(item, empty);
            if (empty) {
            	if (decimalField != null) {
            		decimalField.setValue(new BigDecimal(0));
                }
            	setGraphic(null);
            } else {
            	if (decimalField != null) {
            		decimalField.setValue(getDouble());
                }            	                   
                setGraphic(decimalField);            	
            }
        }

        private void createTextField() {
            decimalField = new DecimalCellField();
            decimalField.setValue(getDouble());
            decimalField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            decimalField.setOnAction((e) -> commitEdit(decimalField.getValue()));
            decimalField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(decimalField.getValue());
                }
            }); 
            decimalField.textProperty().addListener((observable, oldValue, newValue) -> {
				if(oldValue != newValue){
					((TableViewEdit<S>) getTableView()).editRow();
				}			   
			});
            
            decimalField.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
						commitEdit(decimalField.getValue());
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
                
        private BigDecimal getDouble() {
            return getItem() == null ? null : getItem();
        }
    }
}
