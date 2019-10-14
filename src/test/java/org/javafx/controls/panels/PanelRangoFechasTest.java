package org.javafx.controls.panels;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PanelRangoFechasTest extends Application {

	public static void main(String[] args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox box = new VBox();
		box.getChildren().add(new PanelRangoFechas());
		
		Scene scene = new Scene(box,750,500, Color.web("#666970"));
		stage.setScene(scene);       
		stage.show();
	}

}
