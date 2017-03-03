package org.javafx.controls.table.column;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LauncherTest extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("TestTableCustom.fxml"));
    	primaryStage.setTitle("Web View");
        Scene scene = new Scene(root,750,500, Color.web("#666970"));
        primaryStage.setScene(scene);       
        primaryStage.show();
    }
}