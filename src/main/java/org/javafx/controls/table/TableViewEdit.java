package org.javafx.controls.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;

public class TableViewEdit<S> extends TableView<S>{

	private TableColumn<S, Boolean> editado;
	private TableColumn<S, Character> accion;
	private TableColumn<S, Image> accionImg;
	
	@SuppressWarnings("unchecked")
	public TableViewEdit(){
		editado = new TableColumn<S, Boolean>("Editado");
		editado.setMaxWidth(5);
		editado.setVisible(false);
		accion = new TableColumn<S, Character>("Accion");
		accion.setMaxWidth(5);
		accion.setVisible(false);	
		accionImg = new TableColumn<S, Image>();
		accionImg.setMaxWidth(20);
		
		this.getColumns().addAll(editado, accion, accionImg);
	}
}
