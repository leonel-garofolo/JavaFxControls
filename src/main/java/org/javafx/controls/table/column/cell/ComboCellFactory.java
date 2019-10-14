package org.javafx.controls.table.column.cell;

import org.javafx.controls.table.ModelTableViewFx;
import org.javafx.controls.table.TableViewEdit;
import org.javafx.controls.table.column.cell.field.ComboBoxAutoCompleteCellField;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class ComboCellFactory <S extends ModelTableViewFx<S, ?>, T extends Object> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	private ComboBoxEditingCell comboBoxEditingCell;
	private ObservableList<T> data;	
	private EventHandler<ActionEvent> event;
	
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		this.comboBoxEditingCell = new ComboBoxEditingCell();
		return (TableCell<S, T>) comboBoxEditingCell;
	}
	
	public void loadData(ObservableList<T> data){
		this.data = data;
		if(comboBoxEditingCell != null){
			comboBoxEditingCell.loadData(data);
		}
	}		
	
	public ComboBoxEditingCell getComboBoxEditingCell() {
		return comboBoxEditingCell;
	}

	public ObservableList<T> getData() {
		return data;
	}
	
	public void addAction(EventHandler<ActionEvent> event) {
		this.event = event;
	}

	public class ComboBoxEditingCell extends TableCell<S, T> {
        private ComboBoxAutoCompleteCellField<T> comboBox;
      
        public ComboBoxEditingCell(){
        	super();
        	createComboBox();			
        }
        
        public void reload(){
        	comboBox.reload();
        }
        
        public void loadData(ObservableList<T> data){
        	comboBox.setData(data);
        }
        
        @Override
        public void startEdit() {
        	super.startEdit();

            if (comboBox == null) {
                createComboBox();
            }

            setGraphic(comboBox);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        	if (!isEmpty()) {
				super.startEdit();							
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						comboBox.requestFocus();
						comboBox.getEditor().selectAll();
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
        public void updateItem(T item, boolean empty) {
        	super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
            	comboBox.setValue(item);
            	setGraphic(comboBox);
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);        	
        }

        private void createComboBox() {        	
            this.comboBox = new ComboBoxAutoCompleteCellField<T>(getData());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            comboBoxConverter(comboBox);
            comboBox.valueProperty().set(getObject());
            comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            
            //Set value properties object
            comboBox.valueProperty().addListener(new ChangeListener<T>() {
                @Override
                public void changed(ObservableValue<? extends T> obs, T oldValue, T newValue) {
                    // attempt to update property:
                    ObservableValue<T> property = getTableColumn().getCellObservableValue(getIndex());
                    if (property instanceof WritableValue) {
                        ((WritableValue<T>) property).setValue(newValue);
                    }
                }
            });
            
            if(event != null){
            	comboBox.addEventHandler(ActionEvent.ACTION, event);
            }
            
            comboBox.setOnAction((e) -> {
            	if(comboBox.getSelectionModel() != null && comboBox.getSelectionModel().getSelectedItem() !=null){
            		 commitEdit(comboBox.getSelectionModel().getSelectedItem());
            		((TableViewEdit<S>) getTableView()).getSelectionModel().select(getTableRow().getIndex());
 					((TableViewEdit<S>) getTableView()).editRow();
            	}
            });
            
            comboBox.itemsProperty().addListener((observable, oldValue, newValue) -> {
				if(oldValue != null && newValue != null && oldValue.toString() != newValue.toString()){
					((TableViewEdit<S>) getTableView()).getSelectionModel().select(getTableRow().getIndex());
					((TableViewEdit<S>) getTableView()).editRow();
				}			   
			});
            
            /*
            comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            	if(oldValue != null && newValue != null && oldValue.toString() != newValue.toString()){
					((TableViewEdit<S>) getTableView()).getSelectionModel().select(getTableRow().getIndex());
					((TableViewEdit<S>) getTableView()).editRow();
				}			   
			});
			*/
      
            comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER) {
						if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
							commitEdit(comboBox.getSelectionModel().getSelectedItem());
							TableColumn nextColumn = ((TableViewEdit<S>) getTableView()).getNextColumn(!t.isShiftDown(), getTableColumn());
							if (nextColumn != null) {
								getTableView().edit(getTableRow().getIndex(), nextColumn);
							}
						} else if (t.getCode() == KeyCode.ESCAPE) {
							cancelEdit();
						}
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});         
        }  
        
        private void comboBoxConverter(ComboBox<T> comboBox) {
            // Define rendering of the list of values in ComboBox drop down. 
            comboBox.setCellFactory((c) -> {
                return new ListCell<T>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            });
        }                

        private T getObject() {
            return getItem();
        }
        
        public ComboBoxAutoCompleteCellField<T> getComboBox(){
        	return this.comboBox;
        }
    }
}
