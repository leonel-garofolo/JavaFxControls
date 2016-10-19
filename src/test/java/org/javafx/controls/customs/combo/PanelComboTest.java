package org.javafx.controls.customs.combo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PanelComboTest extends Application{
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		PanelVenta panel = new PanelVenta();
		stage.setScene(new Scene(new StackPane(panel)));
		stage.show();
		stage.setTitle("Filtrando um ComboBox");
		stage.setWidth(300);
		stage.setHeight(300);
	}
}
