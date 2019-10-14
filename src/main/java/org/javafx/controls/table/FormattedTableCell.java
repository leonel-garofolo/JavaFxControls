package org.javafx.controls.table;

import java.text.Format;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class FormattedTableCell<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	
    private Format format;
    public FormattedTableCell(Format format) {
        super();
        this.format = format;
    }
    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new TableCell<S, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        };
    }
}