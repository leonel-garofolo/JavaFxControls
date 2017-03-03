package org.javafx.controls.table.column.cell;

import java.math.BigDecimal;

import org.javafx.controls.customs.DecimalField;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DecimalCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return (TableCell<S, T>) new DecimalCell();
	}
	
	class DecimalCell extends TableCell<S, BigDecimal> {

        private DecimalField decimalField;
        private DecimalCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(decimalField);
                decimalField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getItem().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(BigDecimal item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item != null? String.valueOf(item): null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (decimalField != null) {
                        decimalField.setValue(getDouble());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(decimalField);
                } else {
                    setText(item != null? String.valueOf(item): null);
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            decimalField = new DecimalField();
            decimalField.setValue(getDouble());
            decimalField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            decimalField.setOnAction((e) -> commitEdit(decimalField.getValue()));
            decimalField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    commitEdit(decimalField.getValue());
                }
            });
        }

        private BigDecimal getDouble() {
            return getItem() == null ? null : getItem();
        }
    }
}
