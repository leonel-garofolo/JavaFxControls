package org.javafx.controls.table.column;

import java.math.BigDecimal;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty apellido = new SimpleStringProperty();
	private BooleanProperty habilitado = new SimpleBooleanProperty();
	private SimpleObjectProperty<Date> fechaNacimiento = new SimpleObjectProperty<Date>();
	private final SimpleObjectProperty<Typ> typ = new SimpleObjectProperty<Typ>();
	private final SimpleObjectProperty<BigDecimal> ahorro = new SimpleObjectProperty<BigDecimal>();
	
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	public final StringProperty apellidoProperty() {
		return this.apellido;
	}
	
	public final String getApellido() {
		return this.apellidoProperty().get();
	}
	
	public final void setApellido(final String apellido) {
		this.apellidoProperty().set(apellido);
	}
	
	public final BooleanProperty habilitadoProperty() {
		return this.habilitado;
	}
	
	public final boolean isHabilitado() {
		return this.habilitadoProperty().get();
	}
	
	public final void setHabilitado(final boolean habilitado) {
		this.habilitadoProperty().set(habilitado);
	}
	
	public final SimpleObjectProperty<Date> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}
	
	public final Date getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}
	
	public final void setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final SimpleObjectProperty<Typ> typProperty() {
		return this.typ;
	}
	

	public final Typ getTyp() {
		return this.typProperty().get();
	}
	

	public final void setTyp(final Typ typ) {
		this.typProperty().set(typ);
	}							
	
	@Override
	public String toString() {		
		return this.nombre.getValue() + this.apellido.getValue();
	}

	public final SimpleObjectProperty<BigDecimal> ahorroProperty() {
		return this.ahorro;
	}
	

	public final BigDecimal getAhorro() {
		return this.ahorroProperty().get();
	}
	

	public final void setAhorro(final BigDecimal ahorro) {
		this.ahorroProperty().set(ahorro);
	}	
}
