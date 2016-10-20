package org.javafx.controls.customs;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ComboBoxAutoComplete<T> extends ComboBox<T> implements EventHandler<KeyEvent> {
	private String text;	
	private ObservableList<T> data;
	private boolean moveCaretToPos = false;
	private int caretPos;

	
	public ComboBoxAutoComplete(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.setEditable(true);
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {        	
        	this.getEditor().selectAll();
        });
        
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				hide();								
			}
		});
		this.addEventHandler(KeyEvent.KEY_RELEASED, this);	
		 this.focusedProperty().addListener((observable, oldValue, newValue) -> {
		        selectTextIfFocused();
		    });
	    this.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        selectTextIfFocused();
	    });
		    
	}
	
	private void selectTextIfFocused(){
	    Platform.runLater(() -> {
	        if ((this.getEditor().isFocused() || this.isFocused()) && !this.getEditor().getText().isEmpty()) {
	            this.getEditor().selectAll();
	        }
	    });
	}
	
	@FXML
	public void reload(){
		this.data = getItems();
	}
	
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(this.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.DOWN) {
            if(!this.isShowing()) {
                this.show();
            }
            caretPos = -1;
            moveCaret(this.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.BACK_SPACE) {
        	moveCaretToPos = true;
            caretPos = this.getEditor().getCaretPosition();
        } else if(event.getCode() == KeyCode.DELETE) {
        	moveCaretToPos = true;
            caretPos = this.getEditor().getCaretPosition();
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
            return;
        }

        ObservableList<T> list = FXCollections.observableArrayList();
        if(data != null){
        	 for (int i=0; i<data.size(); i++) {
                 if(data.get(i).toString().toLowerCase().contains(
                    this.getEditor().getText().toLowerCase())) {
                     list.add(data.get(i));
                 }
             }
        }
       
        String t = this.getEditor().getText();

        this.setItems(list);
        this.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
            this.show();
        }
		
	}
	
	private void moveCaret(int textLength) {
		if (caretPos == -1) {
			this.getEditor().positionCaret(textLength);
		} else {
			this.getEditor().positionCaret(caretPos);
		}
		moveCaretToPos = false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;		
	}
}
