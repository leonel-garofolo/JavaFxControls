package org.javafx.controls.customs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import org.javafx.utils.JavaFxUtilities;

public class ExplorerControl<T> extends GridCustom{
	private String promptText = "Buscar...";
	
	private Button nuevoElemento = new Button();
	private Button editarElemento = new Button();
	private Button eliminarElemento = new Button();
	private TextField txtSearch = new TextField();
	private ObservableList<T> entries = FXCollections.observableArrayList();
	private Popup popup;
	private ListView<T> list = new ListView<T>();
	
	public ExplorerControl(){
		super();
		initComponents();
	}

	private void initComponents() {	
		String style = " -fx-border-color: transparent;";
	    style += "-fx-border-width: 0;";
	    style += "-fx-background-radius: 0;";	    
	    style += "-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif;";
	    style += "-fx-font-size: 1em; /* 5 */";
	    style += "-fx-text-fill: #828282;";
	    	    	    
		Image img =JavaFxUtilities.getImageSVG(getClass().getResourceAsStream("/image/icon-addlink.svg"));		    
		nuevoElemento.setGraphic(new ImageView(img));
		nuevoElemento.setStyle(style);	
		Image imageEditar = JavaFxUtilities.getImageSVG(getClass().getResourceAsStream("/image/icon-changelink.svg"));
		editarElemento.setGraphic(new ImageView(imageEditar));
		editarElemento.setStyle(style);
		editarElemento.setPrefWidth(5);
		Image imageEliminar = JavaFxUtilities.getImageSVG(getClass().getResourceAsStream("/image/icon-deletelink.svg"));
		eliminarElemento.setGraphic(new ImageView(imageEliminar));
		eliminarElemento.setStyle(style);
		eliminarElemento.setPrefWidth(5);
		
		txtSearch.setPromptText(promptText);
		txtSearch.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldVal, Object newVal) {
				search((String) oldVal, (String) newVal);
				showPopup();
			}
		});
		
		txtSearch.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent event)
            {
                if (KeyCode.UP.equals(event.getCode())
                        || KeyCode.DOWN.equals(event.getCode())
                        || KeyCode.ENTER.equals(event.getCode()))
                {
                    showPopup();
                }
            }
        });
		
		list.setMaxHeight(180);		
		list.setItems(entries);				
		list.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent event)
            {
                if (KeyCode.ENTER.equals(event.getCode()))                        
                {
                	if(!list.getSelectionModel().isEmpty()){
		        		txtSearch.setText(list.getSelectionModel().getSelectedItem().toString());			        		
		        	}		        	
		        	popup.hide();
                }
            }
        });
		
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent click) {

		        if (click.getClickCount() == 2) {
		        	if(!list.getSelectionModel().isEmpty()){
		        		txtSearch.setText(list.getSelectionModel().getSelectedItem().toString());			        		
		        	}		        	
		        	popup.hide();     	
		        }
		    }
		});
		
		this.setHgap(0);
		this.addColumn(1, txtSearch);
		this.addColumn(2, nuevoElemento);
		this.addColumn(3, editarElemento);
		this.addColumn(4, eliminarElemento);
		
		popup = new Popup();
        popup.getContent().add(list);
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);		
	}
	
	private void search(String oldVal, String newVal) {
		if (oldVal != null && (newVal.length() < oldVal.length())) {
			list.setItems(entries);
		}
		String value = newVal.toLowerCase();
		ObservableList<T> subentries = FXCollections.observableArrayList();
		for (Object entry : list.getItems()) {
			boolean match = true;
			T entryText = (T) entry;
			if (!entryText.toString().toLowerCase().contains(value)) {
				match = false;
				continue;
			}
			if (match) {
				subentries.add(entryText);
			}
		}
		list.setItems(subentries);
	}
	
	public void showPopup()
    {
        Parent parent = getParent();
        Bounds childBounds = getBoundsInParent();
        Bounds parentBounds = parent.localToScene(parent.getBoundsInLocal());
        double layoutX = childBounds.getMinX()
                + parentBounds.getMinX() + parent.getScene().getX() + parent.getScene().getWindow().getX();
        double layoutY = childBounds.getMaxY()
                + parentBounds.getMinY() + parent.getScene().getY() + parent.getScene().getWindow().getY();
        popup.show(this, layoutX, layoutY);
    }

	public ObservableList<T> getEntries() {
		return entries;
	}

	public void addAll(ObservableList<T> entries) {
		this.entries.addAll(entries);
	}
	
	public void add(T entrie) {
		this.entries.add(entrie);
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
		this.txtSearch.setPromptText(promptText);
	}
}
