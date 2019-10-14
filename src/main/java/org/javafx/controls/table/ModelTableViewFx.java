package org.javafx.controls.table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public abstract class ModelTableViewFx<S, T> {
	private T entity;
	protected Class<T> persistentClass;
	public BooleanProperty editado = new SimpleBooleanProperty();
	public StringProperty accion = new SimpleStringProperty();
	public SimpleObjectProperty<Image> accionImg = new SimpleObjectProperty<Image>();	
	
	public ModelTableViewFx(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public ModelTableViewFx(T entity){
		super();
		this.entity = entity;
		this.persistentClass=(Class<T>) entity.getClass();		
	}
	
	@SuppressWarnings("unchecked")
	protected void mappedObjectToFx(S entityFx ,T entity){
		Field[] fieldsEntity = entity.getClass().getDeclaredFields();
		for(int z=0; z < fieldsEntity.length; z++){
			fieldsEntity[z].setAccessible(true);
			try 
		    {
		        // Use reflection to get the property	    	
				final Field f = entityFx.getClass().getDeclaredField(fieldsEntity[z].getName());
		        f.setAccessible(true);
		        final Object o = f.get(entityFx);
		        final Field fEntity = entity.getClass().getDeclaredField(fieldsEntity[z].getName());
		        fEntity.setAccessible(true);
		        final Object val = fieldsEntity[z].get(entity);		
		        
		        if(val != null){
		        	if(fEntity.getType() == Integer.class){		        		
		        		((SimpleObjectProperty<Integer>)o).setValue((Integer) val);		        		
		        		continue;
		        	}
		        	if(fEntity.getType() == String.class){
		        		((SimpleObjectProperty<String>)o).setValue((String) val);
		        		continue;
		        	}
		        	if(fEntity.getType() == Boolean.class){
		        		((SimpleObjectProperty<Boolean>)o).setValue((Boolean) val);
		        		continue;
		        	}
		        	if(fEntity.getType() == BigDecimal.class){
		        		((SimpleObjectProperty<BigDecimal>)o).setValue((BigDecimal) val);
		        		continue;
		        	}
		        	if(fEntity.getType() == Date.class){
		        		((SimpleObjectProperty<Date>)o).setValue((Date) val);
		        		continue;
		        	}
		        	if(fEntity.getType() == Image.class){
		        		((SimpleObjectProperty<Image>)o).setValue((Image)  val);
		        		continue;
		        	}
		        	if(fEntity.getType() instanceof Object){
		        		((SimpleObjectProperty<Object>)o).setValue((Object) val);
		        		continue;
		        	}	
		        }		        
		    } catch (Exception ex) {
		        continue;
		    }			
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void mappedFxToObject(S entityFx ,T entity){
		Field[] fieldsEntity = entityFx.getClass().getDeclaredFields();
		for(int z=0; z < fieldsEntity.length; z++){
			fieldsEntity[z].setAccessible(true);
			try 
		    {
		        // Use reflection to get the property	    	
				final Field f = entity.getClass().getDeclaredField(fieldsEntity[z].getName());
		        f.setAccessible(true);		       		             
		        final Object val = fieldsEntity[z].get(entityFx);
		        if(val != null ){		        	
		        	if(f.getType() == Integer.class){		        			        				        	
		        		f.set(entity, ((SimpleObjectProperty<Integer>)val).get());
		        		continue;
		        	}
		        	if(f.getType() == String.class){
		        		
		        		f.set(entity, ((SimpleObjectProperty<String>)val).get());
		        		continue;
		        	}
		        	if(f.getType() == Boolean.class){
		        		f.set(entity, ((SimpleObjectProperty<Boolean>)val).get());
		        		continue;
		        	}
		        	if(f.getType() == BigDecimal.class){
		        		f.set(entity, ((SimpleObjectProperty<BigDecimal>)val).get());
		        		continue;
		        	}
		        	if(f.getType() == Date.class){
		        		f.set(entity, ((SimpleObjectProperty<Date>)val).get());
		        		continue;
		        	}
		        	if(f.getType() == Image.class){
		        		f.set(entity, ((SimpleObjectProperty<Image>)val).get());
		        		continue;
		        	}
		        	if(f.getType() instanceof Object){		        		
		        		f.set(entity, ((SimpleObjectProperty<Object>)val).get());
		        		continue;
		        	}		        	
		        }
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		        continue;
		    }			
		}
	}	
	
	private void setValues(Object objectFrom, Object objectTo){		 
    	 // Modify the value based on the type of property
        if (objectTo instanceof SimpleStringProperty)
        {
            ((SimpleStringProperty)objectTo).setValue(objectFrom.toString());
        }
        else if (objectTo instanceof SimpleIntegerProperty)
        {
        	((SimpleIntegerProperty)objectTo).setValue(Integer.valueOf(objectFrom.toString()));
        }
        else if (objectTo instanceof SimpleBooleanProperty)
        {
        	((SimpleBooleanProperty)objectTo).setValue(Boolean.valueOf(objectFrom.toString()));
        }
        else if (objectTo instanceof SimpleObjectProperty)
        {
        	if(objectFrom instanceof Image){
        		((SimpleObjectProperty<Image>)objectTo).setValue((Image) objectFrom);	
        	}	        	
        	if(objectFrom instanceof BigDecimal){	        		
        		((SimpleObjectProperty<BigDecimal>)objectTo).setValue(BigDecimal.valueOf(Double.valueOf(objectFrom.toString())));	
        	}	
        	if(objectFrom instanceof Date){	        		
        		((SimpleObjectProperty<Date>)objectTo).setValue(((Date)objectFrom));	
        	}	
        }        
	}
	
	public T getEntity(){
		mappedFxToObject((S)this, this.entity);
		return this.entity;
	}
	
	public final BooleanProperty editadoProperty() {
		return this.editado;
	}
	

	public final boolean isEditado() {
		return this.editadoProperty().get();
	}
	

	public final void setEditado(final boolean editado) {
		this.editadoProperty().set(editado);
	}
	

	public final StringProperty accionProperty() {
		return this.accion;
	}
	

	public final String getAccion() {
		return this.accionProperty().get();
	}
	

	public final void setAccion(final String accion) {
		this.accionProperty().set(accion);
	}
	

	public final SimpleObjectProperty<Image> accionImgProperty() {
		return this.accionImg;
	}
	

	public final Image getAccionImg() {
		return this.accionImgProperty().get();
	}
	

	public final void setAccionImg(final Image accionImg) {
		this.accionImgProperty().set(accionImg);
	}		
}
