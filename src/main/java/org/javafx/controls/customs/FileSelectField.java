package org.javafx.controls.customs;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class FileSelectField extends VBox implements EventHandler<ActionEvent>{
	public static char COMPONENT_HORIZONTAL='H';
	public static char COMPONENT_VERTICAL='V';
	
	private int maxValue = 255;
	private String text = "";
	private String promptText = "";
	private StringField field;
	private Button button;
	private char typePosition;
	
	public FileSelectField() {
		super();	
		initComponents();
	}
	
	private void initComponents() {				
		this.field = new StringField();
		this.button = new Button();
		button.setOnAction(this);
		Image img = new Image("image/edit.png");
		ImageView view = new ImageView(img);
		view.setPreserveRatio(true);
		button.setGraphic(view);
				
		field.setPrefWidth(400);
		field.setText(text);
		field.setPromptText(promptText);
		field.setDisable(true);
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(field, button);
		getChildren().add(hBox);
	}	

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.field.setText(text);
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.field.setPromptText(promptText);
	}

	public StringField getField() {
		return field;
	}

	public void setField(StringField field) {
		this.field = field;
	}
	
	public String getValue() {		
		return field.getText();
	}

	public void setValue(String value) {		
		field.setValue(value);
	}

	@Override
	public void handle(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		if(!field.getText().equals("")) {
			File dZip = new File(field.getText());
			chooser.setInitialDirectory(dZip.getParentFile());	
		}
		File file =chooser.showOpenDialog(null);
		if(file != null){
			field.setText(file.getPath());
		}
	}
	
	@Override
	public void requestFocus() {
		super.requestFocus();
		this.field.requestFocus();
	}
	
	public char getTypePosition() {
		return typePosition;
	}
}