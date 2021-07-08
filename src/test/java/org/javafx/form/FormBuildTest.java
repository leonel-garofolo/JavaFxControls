package org.javafx.form;

import java.util.ArrayList;
import java.util.List;

import org.javafx.form.model.FormField;
import org.javafx.form.model.FormFieldType;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FormBuildTest extends Application {

	public static void main(String[] args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {
		List<FormField> fields = new ArrayList<FormField>();
		fields.add(new FormField("field1", "Field 1", FormFieldType.STRING, "aaa", true));
		fields.add(new FormField("field2", "Field 2", FormFieldType.NUMBER, 1223));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		fields.add(new FormField("field3","Field 3", FormFieldType.BOOLEAN, true));
		fields.add(new FormField("field4", "Field 4", FormFieldType.STRING, null));
		fields.add(new FormField("field5", "Subtittle ", FormFieldType.SUBTITLE1, null));
		fields.add(new FormField("field6", "Field 5", FormFieldType.BOOLEAN, true));
		fields.add(new FormField("field7", "Field 6", FormFieldType.STRING, null));
		fields.add(new FormField("field8", "Field 7", FormFieldType.STRING, null));
		
		
		
		final FormBuilder formBuilder = new FormBuilder("Mi Title", fields );
		VBox box = new VBox();
		box.getChildren().add(formBuilder.build());
		
		Scene scene = new Scene(box,750,500, Color.web("#666970"));
		stage.setScene(scene);       
		stage.show();
		
		System.out.println(formBuilder.getValue("field2"));
	}

}
