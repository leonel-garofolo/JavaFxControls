package org.javafx.controls.table.column;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ImageTableColumn<S, T extends Image> extends TableColumnCustom<S, T> {
	
	public ImageTableColumn(){
		setCellFactory(new Callback<TableColumn<S, T>, TableCell<S, T>>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> param) {
                //Set up the ImageView
                final ImageView imageview = new ImageView();
                //Set up the Table
                TableCell<S, T> cell = new TableCell<S, T>() {
                    public void updateItem(T item, boolean empty) {
                        if (item != null) {
                        	imageview.setImage(item);                           
                        }
                    }
                };

                // Attach the imageview to the cell
                cell.setGraphic(imageview);
                return cell;
            }

        });
	}
}
