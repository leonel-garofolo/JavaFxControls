package org.javafx.controls.customs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class StringField extends TextField {
private int maxValue = 255;
	
	public StringField() {
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
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public int getValue() {		
		return Integer.valueOf(this.getText());
	}

	public void setValue(int value) {
		this.setText(String.valueOf(value));
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
