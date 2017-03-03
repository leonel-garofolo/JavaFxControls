package org.javafx.controls.customs;

import java.math.BigDecimal;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class DecimalField extends TextField {	
	private int maxValue = 11;
	
	public DecimalField(){
		super();
		initComponents();
	}
	
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
        
		this.setOnAction((ActionEvent e) -> {	
			if( this.getSkin() instanceof BehaviorSkinBase<?, ?>) {
	            ((BehaviorSkinBase<?, ?>) this.getSkin()).getBehavior().traverseNext();  
	        }			
		});		
	}	
	
	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9,.]*")) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9,.]*")) {
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
		if(this.getText().equals("")){
			return null;
		}else{
			try{
				return new BigDecimal(this.getText());
			}catch (Exception e) {
				return null;
			}
		}
	}
	
	public void setValue(double value) {
		this.setText(String.valueOf(value));
	}

	public void setValue(BigDecimal value) {
		if(value != null){
			this.setText(value.toString());
		}
	}
	
	public void setValue(String value) {		
		this.setText(value);
	}
}
