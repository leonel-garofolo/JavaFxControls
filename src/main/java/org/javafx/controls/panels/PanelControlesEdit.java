package org.javafx.controls.panels;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;

public class PanelControlesEdit extends Pane {
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCancelar;

	public PanelControlesEdit() {
		super();	
		initialize();
	}

	private void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		Image imageDecline = new Image(getClass().getResourceAsStream("/image/save.png"));
		btnGuardar.setGraphic(new ImageView(imageDecline));

		imageDecline = new Image(getClass().getResourceAsStream("/image/cancel.png"));
		btnCancelar.setGraphic(new ImageView(imageDecline));
	}

	public void setScene(Scene scene) {
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
