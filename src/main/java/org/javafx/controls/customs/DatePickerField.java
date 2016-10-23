package org.javafx.controls.customs;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;

public class DatePickerField extends DatePicker {

	public DatePickerField() {		     
       this.setOnAction((ActionEvent e) -> {
			System.out.println("acciono");
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
}
