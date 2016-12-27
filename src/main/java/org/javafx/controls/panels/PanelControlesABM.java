package org.javafx.controls.panels;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PanelControlesABM extends Pane {
	private HBox hBox;
	private Button btnAgregar;
	private Button btnEditar;
	private Button btnEliminar;
	private Button btnInforme;
	

	public PanelControlesABM() {
		super();	
		inicializar();
	}	
	
	private void inicializar() {
		this.hBox = new HBox(); 
		this.btnAgregar = new Button();
		this.btnEditar = new Button();
		this.btnEliminar = new Button();
		this.btnInforme = new Button();
		
		Image imageDecline = new Image(getClass().getResourceAsStream("/image/agregar.png"));
		btnAgregar.setGraphic(new ImageView(imageDecline));
		
		imageDecline = new Image(getClass().getResourceAsStream("/image/edit.png"));
		btnEditar.setGraphic(new ImageView(imageDecline));
		
		imageDecline = new Image(getClass().getResourceAsStream("/image/rubbish-bin.png"));
		btnEliminar.setGraphic(new ImageView(imageDecline));
		
		imageDecline = new Image(getClass().getResourceAsStream("/image/printer.png"));
		btnInforme.setGraphic(new ImageView(imageDecline));
		
		hBox.getChildren().addAll(btnAgregar, btnEditar, btnEliminar, btnInforme);
		this.getChildren().add(hBox);
	}

	public void setScene(Scene scene) {
		btnAgregar.setTooltip(new Tooltip("Agregar Elemento"));
		btnEditar.setTooltip(new Tooltip("Editar Elemento"));
		btnEliminar.setTooltip(new Tooltip("Eliminar Elemento"));
		btnInforme.setTooltip(new Tooltip("Generar Informe"));
		if(scene != null){
			scene.getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN), new Runnable() {
				@Override
				public void run() {
					btnAgregar.fire();
				}
			});

			scene.getAccelerators().put(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN), new Runnable() {
				@Override
				public void run() {
					btnEditar.fire();
				}
			});

			scene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), new Runnable() {
				@Override
				public void run() {
					btnEliminar.fire();
				}
			});
		}
		
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public Button getBtnInforme() {
		return btnInforme;
	}

	
}
