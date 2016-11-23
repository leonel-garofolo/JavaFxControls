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

public class PanelControlesABM extends Pane {
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnInforme;

	public PanelControlesABM() {
		super();
		initialize();
	}

	private void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		Image imageDecline = new Image(getClass().getResourceAsStream("/image/agregar.png"));
		btnAgregar.setGraphic(new ImageView(imageDecline));
		// btnAgregar.getScene().addMnemonic(new Mnemonic(btnAgregar,
		// new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY)));

		imageDecline = new Image(getClass().getResourceAsStream("/image/edit.png"));
		btnEditar.setGraphic(new ImageView(imageDecline));
		// btnEditar.getScene().addMnemonic(new Mnemonic(btnAgregar,
		// new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_ANY)));

		imageDecline = new Image(getClass().getResourceAsStream("/image/rubbish-bin.png"));
		btnEliminar.setGraphic(new ImageView(imageDecline));
		// btnEliminar.getScene().addMnemonic(new Mnemonic(btnAgregar,
		// new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_ANY)));

		imageDecline = new Image(getClass().getResourceAsStream("/image/printer.png"));
		btnInforme.setGraphic(new ImageView(imageDecline));
	}

	public void setScene(Scene scene) {
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
