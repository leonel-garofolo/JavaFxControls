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

public class PanelControlesEdit extends Pane {
	private HBox hBox;
	private Button btnGuardar;
	private Button btnCancelar;

	public PanelControlesEdit() {
		super();	
		initialize();
	}

	private void initialize() {
		this.hBox = new HBox(); 
		this.btnGuardar = new Button();
		this.btnCancelar = new Button();
		
		Image imageDecline = new Image(getClass().getResourceAsStream("/image/save.png"));
		btnGuardar.setGraphic(new ImageView(imageDecline));
		
		imageDecline = new Image(getClass().getResourceAsStream("/image/cancel.png"));
		btnCancelar.setGraphic(new ImageView(imageDecline));
		
		hBox.getChildren().addAll(btnGuardar, btnCancelar);
		this.getChildren().add(hBox);
	}

	public void setScene(Scene scene) {
		btnGuardar.setTooltip(new Tooltip("Guardar Elemento"));
		btnCancelar.setTooltip(new Tooltip("Cancelar Elemento"));
		
		scene.getAccelerators().put(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN), new Runnable() {
			@Override
			public void run() {
				btnGuardar.fire();
			}
		});

		scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ESCAPE), new Runnable() {
			@Override
			public void run() {
				btnCancelar.fire();
			}
		});
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}
}
