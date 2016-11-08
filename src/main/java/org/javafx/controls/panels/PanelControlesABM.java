package org.javafx.controls.panels;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
		Image imageDecline = new Image(getClass().getResourceAsStream("/image/agregar.png"));
		btnAgregar.setGraphic(new ImageView(imageDecline));

		imageDecline = new Image(getClass().getResourceAsStream("/image/edit.png"));
		btnEditar.setGraphic(new ImageView(imageDecline));

		imageDecline = new Image(getClass().getResourceAsStream("/image/rubbish-bin.png"));
		btnEliminar.setGraphic(new ImageView(imageDecline));

		imageDecline = new Image(getClass().getResourceAsStream("/image/printer.png"));
		btnInforme.setGraphic(new ImageView(imageDecline));
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
