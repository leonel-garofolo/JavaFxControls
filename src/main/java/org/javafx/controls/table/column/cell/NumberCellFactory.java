package org.javafx.controls.table.column.cell;

import org.javafx.controls.customs.NumberField;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class NumberCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new NumberCell();
	}
	
	class NumberCell extends TableCell<S, Integer> {

        private NumberField numberField;
        private NumberCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(numberField);
                numberField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getItem().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item != null ? String.valueOf(item): null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (numberField != null) {
                        numberField.setValue(getInteger());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(numberField);
                } else {
                	setText(item != null ? String.valueOf(item): null);
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            numberField = new NumberField();
            numberField.setValue(getInteger());
            numberField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            numberField.setOnAction((e) -> commitEdit(numberField.getValue()));
            numberField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(numberField.getValue());
                }
            });
        }

        private Integer getInteger() {
            return getItem() == null ? null : getItem();
        }
    }
}
