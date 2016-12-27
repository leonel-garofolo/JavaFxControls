package org.javafx.controls.customs;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class NumberField extends TextField {
	private int maxValue = 9;
	
	public NumberField() {
		super();
		initComponents();
	}
	
	//TODO crear parametro de numero de secuencia de foco
	@SuppressWarnings("restriction")
	private void initComponents() {
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
		/*
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
		*/
		this.setOnAction((ActionEvent e) -> {	
			if( this.getSkin() instanceof BehaviorSkinBase<?, ?>) {
	            ((BehaviorSkinBase<?, ?>) this.getSkin()).getBehavior().traverseNext();  
	        }			
		});	
	}	
	
	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9]*")) {
			super.replaceText(start, end, text);
		}				
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9]*")) {
			super.replaceSelection(text);
		}		
	}
	
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public Integer getValue() {		
		if(this.getText() != null && this.getText().equals("")){
			return null;
		}
		try{
			return Integer.valueOf(this.getText());
		}catch (NullPointerException e) {
			return null;
		}
	}

	public void setValue(int value) {
		this.setText(String.valueOf(value));
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
