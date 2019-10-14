package org.javafx.controls.table.column.cell;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.TableViewEdit;
import org.javafx.utils.DateUtils;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class DateCellFactory <S extends ModelTableViewFx<S, ?>, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new DateEditingCell();
	}
	
	class DateEditingCell extends TableCell<S, Date> {

        private DatePicker datePicker;

        private DateEditingCell() {
        	super();
        	createDatePicker();
        }     
        
        @Override
        public void startEdit() {            
            if (datePicker == null) {
            	createDatePicker();            	
			}            
        	if (!isEmpty()) {
				super.startEdit();							
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						datePicker.requestFocus();
						datePicker.getEditor().selectAll();
					}
				});								
			}			
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);            
        }

        @Override
        public void updateItem(Date item, boolean empty) {
        	super.updateItem(item, empty);        	
            if (empty) {
                setGraphic(null);
            } else {            	
            	datePicker.setValue(getDate());
            	setGraphic(datePicker);
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);            
        }

        private void createDatePicker() {        	
            datePicker = new DatePicker(getDate());            
            datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);            
            datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> obs, LocalDate oldValue, LocalDate newValue) {
                    // attempt to update property:
                	
                    ObservableValue<Date> property = getTableColumn().getCellObservableValue(getIndex());
                    if (property instanceof WritableValue) {
                        ((WritableValue<Date>) property).setValue(DateUtils.asDate(newValue));
                    }
                }
            });
            
            
            datePicker.setOnAction((e) -> {    
            	commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())); 
            	if(getTableRow() != null){           		
            		((TableViewEdit<S>) getTableView()).getSelectionModel().select(getTableRow().getIndex());
 					((TableViewEdit<S>) getTableView()).editRow();            		
            	}
            });              
            
            datePicker.getEditor().setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
						commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));						
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

        private LocalDate getDate() {
            return getItem() == null ? LocalDate.now() : getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }         
    }
}
