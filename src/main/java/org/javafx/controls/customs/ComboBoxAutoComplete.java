package org.javafx.controls.customs;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class ComboBoxAutoComplete<T> extends ComboBox<T> implements EventHandler<KeyEvent> {
	private String text;	
	private ObservableList<T> data;
	private boolean moveCaretToPos = false;
	private int caretPos;
	
	public ComboBoxAutoComplete(){		        
        super();
        initComponents();
	}	
		
	public ComboBoxAutoComplete(ObservableList<T> data) {
		 super(data);		
	     initComponents();
	     this.data = data;
	}

	private void initComponents(){		
		setConverter(new StringConverter<T>() {
		    @Override
		    public String toString(T object) {
		        if (object == null) return null;
		        return object.toString();
		    }

			@Override
			public T fromString(String value) {									
				return getSelectionModel().getSelectedItem();
			}		  
		});
		
		data = FXCollections.observableArrayList();		
		this.setEditable(true);      
        
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){					
					
				}				
				hide();								
			}
		});			
		
		this.addEventHandler(KeyEvent.KEY_RELEASED, this);
		this.setOnMousePressed(new EventHandler<MouseEvent>() {    //adding MouseEvent on ComboBox
	        @Override
	        public void handle(MouseEvent event) {
	        	
	        }

	    });
		
		this.focusedProperty().addListener((observable, oldValue, newValue) -> {
		        selectTextIfFocused();
		    });
		
	    this.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    	caretPos = -1;
	    	moveCaret(this.getEditor().getText().length());
	    });		  
	}
	
	private void selectTextIfFocused(){
	    Platform.runLater(() -> {
	        if ((this.getEditor().isFocused() || this.isFocused()) && !this.getEditor().getText().isEmpty()) {
	            this.getEditor().selectAll();	            
	        }
	    });
	}
		
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
        	if(this.getEditor().getCaretPosition() > 0) {
        		if(getSelectionModel() != null) {
        			setValue(null);
        			return;
        		}else {
        			moveCaretToPos = true;
                    caretPos = this.getEditor().getCaretPosition();	
        		}
        	}
        } else if(event.getCode() == KeyCode.DELETE) {
        	if(this.getEditor().getCaretPosition() > 0) {
        		if(getSelectionModel() != null) {
        			setValue(null);
        			return;
        		}else {
        			moveCaretToPos = true;
                    caretPos = this.getEditor().getCaretPosition();
        		}
        	}
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
        if(t != null && !t.equals("")){
        	 this.setItems(list);
        	 //this.reload();
             this.getEditor().setText(t);
             if(!moveCaretToPos) {
                 caretPos = -1;
             }
             moveCaret(t.length());
             if(!list.isEmpty()) {  
             	if(!this.isShowing()){
             		this.show();
             		return;
             	}        	
             }else{        	
             	this.getSelectionModel().clearSelection();
             }
        }else{
        	this.setItems(null);
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
