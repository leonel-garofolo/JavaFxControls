package org.javafx.controls.viewpdf;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LauncherTest extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {
    	WebViewPdf web = new WebViewPdf();    	
    	//web.open("//localhost/c:/Nobilis/Test.pdf");
    	//web.open("c:/Nobilis/Test.pdf");
    	web.open("");
    	primaryStage.setTitle("Web View");
        Scene scene = new Scene(web,750,500, Color.web("#666970"));
        primaryStage.setScene(scene);       
        primaryStage.show();
    }
}