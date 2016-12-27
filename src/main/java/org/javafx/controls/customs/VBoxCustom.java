package org.javafx.controls.customs;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxCustom extends VBox{
		
	protected String label = "";		
	protected Label lbl;	
	
	public VBoxCustom(){
		super();
		initComponents();
	}
	
	private void initComponents() {		
		this.lbl = new Label(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		this.lbl.setText(label);
	}

	public Label getLbl() {
		return lbl;
	}

	public void setLbl(Label lbl) {
		this.lbl = lbl;
	}
	
	public boolean getRequired(){
		return false;
	}
	
	public void setRequired(boolean value) {
		if(value)
			lbl.setStyle("-fx-font-weight: bold;");	
	}
}
