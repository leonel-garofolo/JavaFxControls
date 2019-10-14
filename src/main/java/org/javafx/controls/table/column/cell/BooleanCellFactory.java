package org.javafx.controls.table.column.cell;

import org.javafx.controls.table.column.cell.field.CheckBoxCellField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class BooleanCellFactory<S, T>
          implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> table) {
		return (TableCell<S, T>) new BooleanCell();
	}    
    
    class BooleanCell extends TableCell<S, Boolean> {
    	private ObjectProperty<Callback<Integer, ObservableValue<Boolean>>> selectedStateCallback;
    	private ObservableValue<Boolean> booleanProperty;
        private CheckBoxCellField checkBox;
        
        public BooleanCell() {
        	this.selectedStateCallback = new SimpleObjectProperty(this, "selectedStateCallback");
            checkBox = new CheckBoxCellField();            
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                	if(isEditing())
                		commitEdit(newValue == null ? false : newValue);
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);           
        }
        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();            
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();            
        }
        public void commitEdit(Boolean value) {
            super.commitEdit(value);            
        }
        
        public final ObjectProperty<Callback<Integer, ObservableValue<Boolean>>> selectedStateCallbackProperty() {
    		return this.selectedStateCallback;// 318
    	}
        
        public final Callback<Integer, ObservableValue<Boolean>> getSelectedStateCallback() {
    		return (Callback) this.selectedStateCallbackProperty().get();// 332
    	}
        
        
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
            	checkBox.setSelected(item != null? item:false);            	
            }
            
            this.setGraphic(this.checkBox);// 357
			if (this.booleanProperty instanceof BooleanProperty) {// 359
				this.checkBox.selectedProperty().unbindBidirectional((BooleanProperty) this.booleanProperty);// 360
			}

			ObservableValue arg3 = this.getSelectedProperty();// 362
			if (arg3 instanceof BooleanProperty) {// 363
				this.booleanProperty = arg3;// 364
				this.checkBox.selectedProperty().bindBidirectional((BooleanProperty) this.booleanProperty);// 365
			}
        }
        
        private ObservableValue<?> getSelectedProperty() {
    		return this.getSelectedStateCallback() != null
    				? (ObservableValue) this.getSelectedStateCallback().call(Integer.valueOf(this.getIndex()))
    				: this.getTableColumn().getCellObservableValue(this.getIndex());// 390 391 392
    	}
    }	
}