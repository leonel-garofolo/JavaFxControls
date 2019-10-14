package org.javafx.controls.table.column;

import java.math.BigDecimal;
import java.util.Date;

public class Persona {
	private Integer id;
	private String nombre;
	private String apellido;
	private boolean habilitado;
	private Date fechaNacimiento;
	private Typ typ ;
	private BigDecimal ahorro;
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Typ getTyp() {
		return typ;
	}
	public void setTyp(Typ typ) {
		this.typ = typ;
	}
	public BigDecimal getAhorro() {
		return ahorro;
	}
	public void setAhorro(BigDecimal ahorro) {
		this.ahorro = ahorro;
	}		
	
}
