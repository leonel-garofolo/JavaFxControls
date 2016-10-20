package org.javafx.controls.customs;

import java.io.IOException;
import java.math.BigDecimal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class DecimalField extends TextField {	
	private int maxValue = 11;
	
	public DecimalField(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        textProperty().addListener(new ChangeListener<String>() {

            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (ignore || s1 == null)
                    return;
                if (s1.length() > maxValue) {
                    ignore = true;
                    setText(s1.substring(0, maxValue));
                    ignore = false;
                }
            }
        });
        
        this.setOnAction((ActionEvent e) -> {
		    boolean isThisField = false;
		    for (Node child : getParent().getChildrenUnmodifiable()) {
		        if (isThisField) {

		            //This code will only execute after the current Node
		            if (child.isFocusTraversable() && !child.isDisabled()) {
		                child.requestFocus();

		                //Reset check to prevent later Node from pulling focus
		                isThisField = false;
		            }
		        } else {

		            //Check if this is the current Node
		            isThisField = child.equals(this);
		        }
		    }
		  //Check if current Node still has focus
		    boolean focusChanged = !this.isFocused();
		    if (!focusChanged) {
		        for (Node child : this.getParent().getChildrenUnmodifiable()) {
		            if (!focusChanged && child.isFocusTraversable() && !child.isDisabled()) {
		                child.requestFocus();

		                //Update to prevent later Node from pulling focus
		                focusChanged = true;
		            }
		        }
		    }
		});		    
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9-.?]*")) {
			super.replaceSelection(text);
		}
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getValue() {		
		return new BigDecimal(this.getText());
	}

	public void setValue(BigDecimal value) {
		this.setText(value.toString());
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
