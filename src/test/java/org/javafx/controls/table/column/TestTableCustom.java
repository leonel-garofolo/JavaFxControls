package org.javafx.controls.table.column;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class TestTableCustom implements Initializable{
	
	@FXML
	private TableView<Persona> tblPersona;

	@FXML
	private BooleanTableColumn<Persona, Boolean> colCheck;	
	
	@FXML
	private ComboTableColumn<Persona, Typ> colTyp;
	
	
	private final ObservableList<Typ> typData
    = FXCollections.observableArrayList(
            new Typ("Hund"),
            new Typ("Fuchs"),
            new Typ("Esel"));
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Persona p = new Persona();
		p.setId(1);		
		p.setNombre("Leonel");
		p.setApellido("Garofolo");
		p.setHabilitado(true);
		p.setTyp(typData.get(0));
		//p.setAhorro(new BigDecimal(5.5));
		tblPersona.getItems().add(p);
		
		colTyp.loadData(typData);
	}
	
	@FXML
	private void hanledTest(ActionEvent event) {	
		if( tblPersona.getSelectionModel().getSelectedItem() != null){			
			System.out.println("nombre: " + tblPersona.getSelectionModel().getSelectedItem().getNombre());
			System.out.println("apellido: " + tblPersona.getSelectionModel().getSelectedItem().getApellido());
			System.out.println("fecha nacimiento: " + tblPersona.getSelectionModel().getSelectedItem().getFechaNacimiento());
			System.out.println("hab: " + tblPersona.getSelectionModel().getSelectedItem().isHabilitado());			
		}
	}
}
