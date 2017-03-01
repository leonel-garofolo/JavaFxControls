package org.javafx.controls.viewpdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class WebViewPdf extends Region  {		    
	private String url;
	final WebView browser = new WebView();
	final WebEngine engine = browser.getEngine();
	private String realPath;	
    
	public WebViewPdf() {
		this.engine.setJavaScriptEnabled(true);
		this.url = getClass().getResource("/web/viewer.html").getPath();
        
		this.engine.setJavaScriptEnabled(true);
		this.engine.load(url);

		this.engine.getLoadWorker()
                .stateProperty()
                .addListener((observable, oldValue, newValue) -> {
                    // to debug JS code by showing console.log() calls in IDE console
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("java", new JSLogListener());
                    engine.executeScript("console.log = function(message){ java.log(message); };");                   
                });
		
		this.engine.locationProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
              String downloadableExtension = null;  // todo I wonder how to find out from WebView which documents it could not process so that I could trigger a save as for them?
              String[] downloadableExtensions = { "download"};
              for (String ext: downloadableExtensions) {
                if (newLoc.endsWith(ext)) {
                  downloadableExtension = ext;                  
                  break;
                }
              }
              if (downloadableExtension != null) {  
                int filenameIdx = newLoc.lastIndexOf("/") + 1;
                if (filenameIdx != 0 && realPath != null) {
                	FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save PDF");
                  
                    File file = fileChooser.showSaveDialog(new Stage());
                    if (file != null) {
                    	try{
                    		URL urlPath = new URL(realPath);
                        	InputStream in = urlPath.openStream();
                        	Files.copy(in, Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
                        	in.close();
                    	}catch (IOException e) {
							e.printStackTrace();
						}                    	
                    }
                }
              }
            }
		});
                 
		getChildren().add(browser);		
    }	
    
    public void open(String path){  
    	this.realPath= path;
    	this.engine.load("file:///" + url + "?file=" + path);          	
    }
    
    public class JSLogListener {
        public void log(String text) {
            System.out.println(text);
        }
    }
    
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}