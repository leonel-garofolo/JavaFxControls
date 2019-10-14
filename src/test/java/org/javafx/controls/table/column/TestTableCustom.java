package org.javafx.controls.table.column;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import org.javafx.controls.table.TableViewEdit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TestTableCustom implements Initializable{
	
	@FXML
	private TableViewEdit<PersonaFx> tblPersona;
	
	@FXML
	private ComboTableColumn<PersonaFx, Typ> colTyp;
	
	@FXML
	private BooleanTableColumn<PersonaFx, Boolean> colCheck;
	
	
	private final ObservableList<Typ> typData
    = FXCollections.observableArrayList(
            new Typ("Hund"),
            new Typ("Fuchs"),
            new Typ("Esel"));
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		tblPersona.setTableClass(PersonaFx.class);
		Persona p = new Persona();
		
		p.setId(1);		
		p.setNombre("Leonel");
		p.setApellido("Garofolo");
		p.setHabilitado(true);
		p.setTyp(typData.get(0));
		p.setAhorro(new BigDecimal(5.5));
		tblPersona.getItems().add(new PersonaFx(p));				
		colTyp.addAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Event222");
				
			}
		});
		colTyp.loadData(typData);
		tblPersona.getAddMenuItem().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Persona p = new Persona();				
				tblPersona.getItems().add(new PersonaFx(p));
			}
		});
		
		
	}
	
	@FXML
	private void hanledTest(ActionEvent event) {	
		if( tblPersona.getSelectionModel().getSelectedItem() != null){			
			PersonaFx per = tblPersona.getSelectionModel().getSelectedItem();			
			System.out.println("nombre: " + per.getNombre());
			System.out.println("apellido: " + per.getApellido());
			System.out.println("fecha nacimiento: " + per.getFechaNacimiento());
			System.out.println("hab: " + per.getHabilitado());
			System.out.println("hab: " + tblPersona.getSelectionModel().getSelectedItem().getHabilitado());	
		}
	}
}
