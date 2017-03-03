package org.javafx.controls.table.column.cell;

import org.javafx.controls.customs.ComboBoxAutoComplete;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ComboCellFactory <S, T extends Object> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	private ComboBoxEditingCell comboBoxEditingCell;
	private ObservableList<T> data;
	
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		comboBoxEditingCell = new ComboBoxEditingCell();
		return (TableCell<S, T>) new ComboBoxEditingCell();
	}
	
	public void loadData(ObservableList<T> data){
		this.data = data;		
	}		
	
	public ComboBoxEditingCell getComboBoxEditingCell() {
		return comboBoxEditingCell;
	}

	public ObservableList<T> getData() {
		return data;
	}

	class ComboBoxEditingCell extends TableCell<S, T> {

        private ComboBoxAutoComplete<T> comboBox;

        private ComboBoxEditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                setText(null);
                setGraphic(comboBox);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getObject().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (comboBox != null) {
                        comboBox.setValue(getObject());
                    }
                    setText(getObject().toString());
                    setGraphic(comboBox);
                } else {
                    setText(getObject().toString());
                    setGraphic(null);
                }
            }
        }

        private void createComboBox() {
            comboBox = new ComboBoxAutoComplete<T>(getData());
            comboBoxConverter(comboBox);
            comboBox.valueProperty().set(getObject());
            comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            comboBox.setOnAction((e) -> {
                commitEdit(comboBox.getSelectionModel().getSelectedItem());
            });
//            comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
//                }
//            });
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
    }
}
