package org.javafx.controls.table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.javafx.controls.table.column.BooleanTableColumn;
import org.javafx.controls.table.column.ImageTableColumn;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class TableViewEdit<S extends ModelTableViewFx<S, ?>> extends TableView<S> {
	private BooleanTableColumn<S, Boolean> editado;
	private TableColumn<S, String> accion;
	private ImageTableColumn<S, Image> accionImg;
	
	private MenuItem addMenuItem = new MenuItem("Agregar Elemento");
	private MenuItem removeMenuItem = new MenuItem("Eliminar Elemento");
	private ContextMenu menuFolder = new ContextMenu();
	private Class<S> tableClass;		
	
	@SuppressWarnings("unchecked")
	public TableViewEdit() {		
		removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				removeRow();
			}
		});		
		
		menuFolder.getItems().addAll(addMenuItem, removeMenuItem);
		setContextMenu(menuFolder);
		
		editado = new BooleanTableColumn<S, Boolean>();
		editado.setCellValueFactory(new PropertyValueFactory<S,Boolean>("editado"));
		editado.setVisible(false);		
		accion = new TableColumn<S, String>("Accion");
		accion.setCellValueFactory(new PropertyValueFactory<S,String>("accion"));
		accion.setVisible(false);		
		accionImg = new ImageTableColumn<S, Image>();
		accionImg.setCellValueFactory(new PropertyValueFactory<S,Image>("accionImg"));
		accionImg.setPrefWidth(21);
		/*
		getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
            	
                return;
            }                
        });
        */		
		this.getColumns().addAll(editado, accion, accionImg);
	}
	
	public void setEventAddRow(EventHandler<ActionEvent> e){
		addMenuItem.setOnAction(e);
	}
	
	public int getRowSelected(){
		if(getFocusModel() != null && getFocusModel().getFocusedCell() != null){
			return getFocusModel().getFocusedCell().getRow();		
		}
		return -1;
	}
	
	public int getColumnSelected(){
		if(getFocusModel() != null && getFocusModel().getFocusedCell() != null){
			return getFocusModel().getFocusedCell().getColumn();	
		}
		return -1;
	}

	public void addRow(S entity) {
		// clear current selection
		getSelectionModel().clearSelection();	
		getItems().add(entity);
		getSelectionModel().select(entity);		
	
		int rowSelected = getFocusModel().getFocusedCell().getRow();		
		setValue(rowSelected, 0, true);
		setValue(rowSelected, 1, "C");
		setValue(rowSelected, 2, new Image(getClass().getResourceAsStream("/image/documentation.png")));		
	}	
	
	private void removeRow() {	
		if(this.getItems() != null && this.getItems().size() > 0){
			setValue(getFocusModel().getFocusedCell().getRow(), 0, true);
			setValue(getFocusModel().getFocusedCell().getRow(), 1, "D");
			setValue(getFocusModel().getFocusedCell().getRow(), 2, new Image(getClass().getResourceAsStream("/image/document-delete.png")));
		}		
	}	
	
	public void editRow(){				
		if(getSelectionModel().getSelectedItem() != null && (!getSelectionModel().getSelectedItem().isEditado() && (getSelectionModel().getSelectedItem().getAccion() == null || !getSelectionModel().getSelectedItem().getAccion().equals("C")))){
			setValue(getFocusModel().getFocusedCell().getRow(), 0, true);
			setValue(getFocusModel().getFocusedCell().getRow(), 1, "U");
			setValue(getFocusModel().getFocusedCell().getRow(), 2, new Image(getClass().getResourceAsStream("/image/document-edit.png")));
		}		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setValue(int row, int col, Object val)
	{
	    final S selectedRow = getItems().get(row);
	    final TableColumn<S,?> selectedColumn = getColumns().get(col);
	   
	    // Lookup the propery name for this column
	    final String propertyName =   ((PropertyValueFactory)selectedColumn.getCellValueFactory()).getProperty();
	    try 
	    {
	        // Use reflection to get the property	
	    	tableClass.getDeclaredFields();
	    	Field f = null;
	    	for(int i=0; i < tableClass.getFields().length; i++){
	    		if(tableClass.getFields()[i].getName().equals(propertyName)){
	    			 f= tableClass.getFields()[i];
	    			 break;
	    		}
	    	}
	    	if(f == null){
	    		f = tableClass.getDeclaredField(propertyName);
	    	}	        
	        f.setAccessible(true);
	        final Object o = f.get(selectedRow);
	        if(val != null){
	        	 // Modify the value based on the type of property
		        if (o instanceof SimpleStringProperty)
		        {
		            ((SimpleStringProperty)o).setValue(val.toString());
		        }
		        else if (o instanceof SimpleIntegerProperty)
		        {
		        	((SimpleIntegerProperty)o).setValue(Integer.valueOf(val.toString()));
		        }
		        else if (o instanceof SimpleBooleanProperty)
		        {
		        	((SimpleBooleanProperty)o).setValue(Boolean.valueOf(val.toString()));
		        }
		        else if (o instanceof SimpleObjectProperty)
		        {
		        	if(val instanceof Image){	        		
		        		((SimpleObjectProperty<Image>)o).setValue((Image) val);	
		        	}	        	
		        	if(val instanceof BigDecimal){	        		
		        		((SimpleObjectProperty<BigDecimal>)o).setValue(BigDecimal.valueOf(Double.valueOf(val.toString())));	
		        	}	        	
		        }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public TableColumn<S, ?> getNextColumn(boolean forward, TableColumn<S, ?> columnCell) {		
		List<TableColumn<S, ?>> columns = new ArrayList<>();
		for (TableColumn<S, ?> column : getColumns()) {
			columns.addAll(getLeaves(column));
		}
		// There is no other column that supports editing.
		if (columns.size() < 2) {
			return null;
		}
			
		int currentIndex = columns.indexOf(columnCell);
		//int currentIndex = columns.indexOf(pos.getTableColumn());
		int nextIndex = currentIndex;
		if (forward) {
			nextIndex++;
			if (nextIndex > columns.size() - 1) {
				nextIndex = 0;
			}
		} else {
			nextIndex--;
			if (nextIndex < 0) {
				nextIndex = columns.size() - 1;
			}
		}
		return columns.get(nextIndex);
	}

	private List<TableColumn<S, ?>> getLeaves(TableColumn<S, ?> root) {
		List<TableColumn<S, ?>> columns = new ArrayList<>();
		if (root.getColumns().isEmpty()) {
			// We only want the leaves that are editable.
			if (root.isEditable()) {
				columns.add(root);
			}
			return columns;
		} else {
			for (TableColumn<S, ?> column : root.getColumns()) {
				columns.addAll(getLeaves(column));
			}
			return columns;
		}
	}	

	public Class<S> getTableClass() {
		return tableClass;
	}

	public void setTableClass(Class<S> tableClass) {
		this.tableClass = tableClass;
	}	

	public MenuItem getAddMenuItem() {
		return addMenuItem;
	}	
}
