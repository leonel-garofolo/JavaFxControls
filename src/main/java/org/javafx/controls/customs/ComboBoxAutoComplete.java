package org.javafx.controls.customs;

import java.io.IOException;

import javafx.beans.property.StringProperty;
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
	
	private static String typedText;
	private static StringBuilder sb = new StringBuilder();
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
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				hide();
			}
		});
		this.addEventHandler(KeyEvent.KEY_RELEASED, this);
	}
	
	@FXML
	public void reload(){
		this.data = getItems();
	}
	
	public void handle(KeyEvent event) {
		String keyPressed = event.getCode().toString().toLowerCase();

		if ("space".equals(keyPressed)) {
			typedText = " ";
		} else if ("shift".equals(keyPressed) || "command".equals(keyPressed) || "alt".equals(keyPressed)) {

			return;
		} else {
			typedText = event.getCode().toString().toLowerCase();
		}

		if (event.getCode() == KeyCode.UP) {
			caretPos = -1;
			moveCaret(this.getEditor().getText().length());
			return;
		} else if (event.getCode() == KeyCode.DOWN) {
			if (!this.isShowing()) {
				this.show();
			}
			caretPos = -1;
			moveCaret(this.getEditor().getText().length());
			return;
		} 				
		else if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
			moveCaretToPos = true;
			caretPos = this.getEditor().getCaretPosition();					
		} else if (event.getCode().equals(KeyCode.TAB)) {		
			typedText = null;
			sb.delete(0, sb.length());
			return;
		} else if (event.getCode() == KeyCode.LEFT || event.isControlDown() || event.getCode() == KeyCode.HOME
				|| event.getCode() == KeyCode.END || event.getCode() == KeyCode.RIGHT) {

			return;
		}

		if (typedText == null) {
			typedText = this.getEditor().getText().toLowerCase();
			sb.append(typedText);

		} else {
			System.out.println("sb:" + sb);
			System.out.println("tt:" + typedText);

			sb.append(typedText);

		}

		ObservableList<T> list = FXCollections.observableArrayList();
		for (T aData : data) {
			if(aData.toString().toLowerCase().contains(this.getEditor().getText().toLowerCase())) 
				list.add(aData);													
			
		}

		this.setItems(list);
		/*
		if (mode.equals(AutoCompleteMode.STARTS_WITH)){
			this.getEditor().setText(some);
			this.getEditor().positionCaret(sb.length());
			this.getEditor().selectEnd();	
		}
		*/
		
		if (!moveCaretToPos) {
			caretPos = -1;
		}

		if (!list.isEmpty()) {
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
		System.out.println(text);
	}
	
	
}
