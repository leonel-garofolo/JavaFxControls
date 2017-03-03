package org.javafx.controls.table.column.cell;

import org.javafx.controls.customs.StringField;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class StringCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new StringCell();
	}
	
	class StringCell extends TableCell<S, String> {

        private StringField stringField;

        private StringCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(stringField);
                stringField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (stringField != null) {
                        stringField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(stringField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            stringField = new StringField();
            stringField.setValue(getString());
            stringField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            stringField.setOnAction((e) -> commitEdit(stringField.getText()));
            stringField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(stringField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }
}
