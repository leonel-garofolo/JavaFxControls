package org.javafx.controls.table.column;


import org.javafx.controls.customs.ComboBoxAutoComplete;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;

public class ComboBoxAutoTableCell<S, T> extends ComboBoxTableCell<S, T> {
	 public ComboBoxAutoComplete<T> call(TableColumn<S, T> p) {
        return new ComboBoxAutoComplete<T>();
    }
}
