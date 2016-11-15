package org.javafx.controls.panels;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PanelControlesEdit extends Pane {
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCancelar;
	
	public PanelControlesEdit() {
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

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}
}
