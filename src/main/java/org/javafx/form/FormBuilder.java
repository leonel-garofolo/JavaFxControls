package org.javafx.form;

import java.util.List;

import org.javafx.controls.customs.CheckBoxField;
import org.javafx.controls.customs.DirectorySelectField;
import org.javafx.controls.customs.FileSelectField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;
import org.javafx.controls.customs.view.ComboBoxAutoCompleteView;
import org.javafx.form.model.FormField;
import org.javafx.form.model.FormFieldList;
import org.javafx.style.TextStyle;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FormBuilder extends ScrollPane {
	private static final int PADDING= 25;
	private static final int V_GAP= 10;
	private static final int H_GAP= 25;
	private VBox vbox;	
	private GridPane gridPane;
	private List<FormField> fields;
	
	public FormBuilder(String title, List<FormField> fields) {		
		this.vbox = new VBox();
		
		HBox hBox= new HBox();
		Label lblTitle = new Label(title);
		lblTitle.setId("lblTitle");
		hBox.getChildren().add(lblTitle);
		hBox.setPrefWidth(Double.MAX_VALUE);
		//addStackPane(hBox);
		
		this.vbox.getChildren().add(hBox);
		this.vbox.getChildren().add(new Separator());
		this.vbox.setSpacing(10);
		this.gridPane = new GridPane();
		this.gridPane.setVgap(V_GAP);
		this.gridPane.setHgap(H_GAP);
		this.vbox.getChildren().add(this.gridPane);
		this.vbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		this.fields = fields;		
		setContent(this.vbox);
		fitToHeightProperty().set(true);
		fitToWidthProperty().set(true);
	}
	
	@SuppressWarnings("rawtypes")
	public Object getValue(String id) {
		FormField ff = null;
		for(FormField formField: fields) {
			if(formField.getId() == null)
				continue; 
			
			if(formField.getId().equals(id)) {
				ff= formField;
				break;
			}
		}
		if(ff != null) {
			ObservableList<Node> nodos = this.gridPane.getChildren();
			for (Node node : nodos) {
				if(node instanceof VBox) {
					final ObservableList<Node> vBoxChildren = ((VBox) node).getChildren();
					Object value = findValue(vBoxChildren, ff, id);
					if(value == null)
						continue;
					return value;
				}	
				
				if(node instanceof HBox) {
					final ObservableList<Node> vBoxChildren = ((HBox) node).getChildren();
					for(Node vBoxNode: vBoxChildren) {
						if(vBoxNode.getId() != null && vBoxNode.getId().equals(id)) {
							switch (ff.getType()) {							
							case BOOLEAN:
								return ((CheckBoxField)vBoxNode).isSelected();							
							default:
								break;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private Object findValue(ObservableList<Node> vBoxChildren, FormField ff, String id) {
		for(Node vBoxNode: vBoxChildren) {
			if(vBoxNode.getId() != null && vBoxNode.getId().equals(id)) {
				switch (ff.getType()) {
				case STRING:
					return ((StringField)vBoxNode).getValue();
				case NUMBER:
					return ((NumberField)vBoxNode).getValue();
				case BOOLEAN:
					return ((CheckBoxField)vBoxNode).isSelected();
				case LIST:
					return ((ComboBoxAutoCompleteView)vBoxNode).getValue();
				case DIRECTORY:
					return ((DirectorySelectField)vBoxNode).getValue();
				case FILE:
					return ((FileSelectField)vBoxNode).getValue();
				default:
					findValue(((VBox) vBoxNode).getChildren(), ff, id);
					break;
				}
			}
		}
		return null;
	}
		
	public FormBuilder build() {		
		this.buildGridPane();
		return this;
	}
	
	private void addStackPane(HBox hb) {
	    StackPane stack = new StackPane();
	    Rectangle helpIcon = new Rectangle(30.0, 25.0);
	    helpIcon.setArcHeight(3.5);
	    helpIcon.setArcWidth(3.5);

	    Label helpText = new Label("X");
	    helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));	 
	    stack.getChildren().add(helpText);
	    stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
	    StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"

	    hb.getChildren().add(stack);            // Add to HBox from Example 1-2
	    HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
	}
	
	private void buildGridPane() {
		int colMax = 0;
		int colIndex = 0;
		int rowIndex = 0;		
		for(FormField ff: fields) {
			if(ff.getType() == null) 
				continue;
			switch (ff.getType()) {
			case NEW_ROW:
				rowIndex++;
				colIndex = 0;
				continue;
			case SUBTITLE1:
				rowIndex++;
				colIndex = 0;			
				Label subTitle1 = new Label(ff.getLabel());
				subTitle1.setId("lblSubtitle1");																				
				this.gridPane.add(subTitle1, colIndex, rowIndex, 2, 1);
				
				rowIndex++;
				colIndex = 0;
				continue;
			case SUBTITLE2:
				rowIndex++;
				colIndex = 0;			
				Label subTitle2 = new Label(ff.getLabel());
				subTitle2.setId("lblSubtitle2");																			
				this.gridPane.add(subTitle2, colIndex, rowIndex, 2, 1);
				
				rowIndex++;
				colIndex = 0;				
				continue;

			default:
				break;
			}		
			
			try {
				switch (ff.getType()) {	
				case LINE:		
					colIndex = 0;
					this.gridPane.add(new Separator(), colIndex, rowIndex, colMax, 1);
					break;
				case BUTTON:
					this.gridPane.add(buildFieldVertical(ff), colMax - 1, rowIndex, 2,1);
					break;
				case BOOLEAN:
					this.gridPane.add(buildFieldHorizontal(ff), colIndex, rowIndex);
					break;
				case DIRECTORY:
				case FILE:
					this.gridPane.add(buildFieldVertical(ff), colIndex, rowIndex, 6, 1);
					break;
				default:
					this.gridPane.add(buildFieldVertical(ff), colIndex, rowIndex);
					break;
				}
				colIndex++;		
				if(colIndex >= colMax )
					colMax = colIndex;	
			}catch (Exception e) {
				throw new NullPointerException("Error en el campo: " + ff.getId());
			}
			
		}			
	}
	
	
	private VBox buildFieldVertical(FormField formField) {		
		VBox vBox = new VBox();				
		switch (formField.getType()) {
		case STRING:
			vBox.getChildren().add(buildLabel(formField));
			final StringField stringField = new StringField();
			stringField.setId(formField.getId());
			stringField.setPrefWidth(200);
			if(formField.getValue()!= null)
				stringField.setValue((String) formField.getValue());
			vBox.getChildren().add(stringField);
			break;
		case NUMBER:
			vBox.getChildren().add(buildLabel(formField));
			final NumberField numberField= new NumberField();
			numberField.setId(formField.getId());
			if(formField.getValue()!= null)
				numberField.setValue(Integer.valueOf(formField.getValue().toString()));
			vBox.getChildren().add(numberField);
			break;				
		case LIST:
			final ComboBoxAutoCompleteView<Object> listField = new ComboBoxAutoCompleteView<Object>(formField.getLabel());			
			listField.addAllItem(((FormFieldList)formField).getList());
			listField.setId(formField.getId());
			if(formField.getValue() != null)
				listField.setValue(formField.getValue());
			vBox.getChildren().add(listField);
			break;
		case DIRECTORY:
			vBox.getChildren().add(buildLabel(formField));
			final DirectorySelectField directorySelectFieldVBox = new DirectorySelectField();
			directorySelectFieldVBox.setId(formField.getId());
			directorySelectFieldVBox.setPrefWidth(300);
			vBox.getChildren().add(directorySelectFieldVBox);
			break;
		case FILE:
			vBox.getChildren().add(buildLabel(formField));
			final FileSelectField fileSelectField = new FileSelectField();
			fileSelectField.setId(formField.getId());
			fileSelectField.setPrefWidth(300);
			vBox.getChildren().add(fileSelectField);
			break;			
		case BUTTON:
			vBox.getChildren().add(new Label(""));
			final Button btn = new Button(formField.getLabel());
			btn.setPrefWidth(100);
			vBox.getChildren().add(btn);
			break;
		default:
			break;
		}		
		return vBox;
	}
	
	private Label buildLabel(FormField formField) {
		Label label = new Label(formField.getLabel());
		if(formField.isRequired())
			label.setStyle(TextStyle.STYLE_BOLD);
		
		return label;
	}
	
	private HBox buildFieldHorizontal(FormField formField) {		
		HBox hBox = new HBox();
		Node node= null;
		switch (formField.getType()) {		
		case BOOLEAN:
			final Label label = buildLabel(formField);
			label.setPrefWidth(150);
			hBox.getChildren().add(label);
			CheckBoxField checkBoxField = new CheckBoxField();
			if(formField.getValue()!= null)
				checkBoxField.setSelected(Boolean.valueOf(formField.getValue().toString()));
			node = checkBoxField;
			break;
		default:
			break;
		}		
		if(formField.getId() != null) {
			node.setId(formField.getId());
		}
		hBox.getChildren().add(node);
		return hBox;
	}
}
