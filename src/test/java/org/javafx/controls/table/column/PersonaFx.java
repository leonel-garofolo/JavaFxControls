package org.javafx.controls.table.column;

import java.math.BigDecimal;
import java.util.Date;

import org.javafx.controls.table.ModelTableViewFx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class PersonaFx extends ModelTableViewFx<PersonaFx, Persona>{	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty apellido = new SimpleStringProperty();
	private BooleanProperty habilitado = new SimpleBooleanProperty();
	private SimpleObjectProperty<Date> fechaNacimiento = new SimpleObjectProperty<Date>();
	private final SimpleObjectProperty<Typ> typ = new SimpleObjectProperty<Typ>();
	private final SimpleObjectProperty<BigDecimal> ahorro = new SimpleObjectProperty<BigDecimal>();
		
	public PersonaFx(){
		super();
	}
	
	public PersonaFx(Persona p){		
		super(p);			
		mappedObjectToFx(this, p);	
		this.accion.set("N");
		this.editado.set(false);
		this.setAccionImg(new Image(getClass().getResourceAsStream("/image/documentation.png")));
	}
	
	@Override
	public String toString() {		
		return this.nombre.getValue() + this.apellido.getValue();		
	}

	public final SimpleObjectProperty<BigDecimal> ahorroProperty() {
		return this.ahorro;
	}

	public IntegerProperty idProperty() {
		return this.id;
	}
	
	public int getId() {
		return this.idProperty().get();
	}
	

	public void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public String getNombre() {
		return this.nombreProperty().get();
	}
	

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public StringProperty apellidoProperty() {
		return this.apellido;
	}
	

	public String getApellido() {
		return this.apellidoProperty().get();
	}
	

	public void setApellido(final String apellido) {
		this.apellidoProperty().set(apellido);
	}
	

	public BooleanProperty habilitadoProperty() {
		return this.habilitado;
	}
	
	public boolean getHabilitado() {
		return this.habilitadoProperty().get();
	}
	

	public void setHabilitado(final boolean habilitado) {
		this.habilitadoProperty().set(habilitado);
	}
	

	public SimpleObjectProperty<Date> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}
	

	public Date getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}
	

	public void setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}
	

	public SimpleObjectProperty<Typ> typProperty() {
		return this.typ;
	}
	

	public Typ getTyp() {
		return this.typProperty().get();
	}
	

	public void setTyp(final Typ typ) {
		this.typProperty().set(typ);
	}

	
}
