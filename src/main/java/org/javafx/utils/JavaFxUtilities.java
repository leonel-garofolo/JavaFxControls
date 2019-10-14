package org.javafx.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.javafx.controls.customs.CheckBoxField;
import org.javafx.controls.customs.ComboBoxAutoComplete;
import org.javafx.controls.customs.ComboBoxAutoFieldVBox;
import org.javafx.controls.customs.DatePickerField;
import org.javafx.controls.customs.DatePickerFieldVBox;
import org.javafx.controls.customs.DecimalFieldVBox;
import org.javafx.controls.customs.GridCustom;
import org.javafx.controls.customs.NumberFieldVBox;
import org.javafx.controls.customs.StringFieldVBox;
import org.javafx.controls.panels.PanelRangoFechas;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFxUtilities {
	public static enum MessageType {
		NONE, INFORMATION, WARNING, ASK, ERROR;
	}
	
	public static String MESSAGE_INFO ="INFO";
	public static String MESSAGE_WARN ="WARN";
	public static String MESSAGE_ERROR ="ERROR";
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private static SimpleDateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	protected FXMLLoader loadFxml(ResourceBundle resources, URL resourceFxml, InputStream resourceAsStream) {
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(resources);
		loader.setLocation(resourceFxml);
		try {
			loader.<Parent>load(resourceAsStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return loader;
	}	
	
	public static void message(MessageType typeMsg, String title, String msg){		
		Alert alert = null;
		switch (typeMsg) {
		case NONE:
			alert = new Alert(AlertType.NONE);
			break;
		case INFORMATION:
			alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Notificación.");
			break;
		case WARNING:
			alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Atención.");
			break;
		case ASK:
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Por favor, confirma la pregunta.");
			break;
		case ERROR:
			alert = new Alert(AlertType.ERROR);
			break;
		default:
			alert = new Alert(AlertType.NONE);
			break;
		}		
		alert.setTitle(title);		
		alert.setContentText(msg);
		alert.showAndWait();
	}	
	
	public static boolean messageAsk(String title, String msg){		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Por favor, confirma la pregunta.");		
		alert.setTitle(title);		
		alert.setContentText(msg);		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		   	return true;
		} else {
			return false;
		}
	}	
	
	protected void limpiarController(Object controller) {
		java.lang.reflect.Field[] declaredFields = controller.getClass().getDeclaredFields();
		for (java.lang.reflect.Field field : declaredFields) {			
			field.setAccessible(true);
			if(field.getType() == NumberFieldVBox.class
					|| field.getType() == StringFieldVBox.class
					|| field.getType() == DatePickerFieldVBox.class
					|| field.getType() == DecimalFieldVBox.class
					|| field.getType() == ComboBoxAutoFieldVBox.class){			
				setNullField(field);				
			}else{
				setNullFieldSpecial(field);		
			}	
				
		}
	}
	
	public static Stage newWindow(FXMLLoader loader, String title){
		Stage stage = new Stage();			
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(loader.getRoot()));
      	return stage;
	}
	
	public static void closeTab(Tab tab){
		EventHandler<Event> handler = tab.getOnClosed();
		if (null != handler) {
			handler.handle(null);
		} else {
			tab.getTabPane().getTabs().remove(tab);
		}
	}
	
	private void setNullField(java.lang.reflect.Field field){
		GridCustom value;
		try {
			value = (GridCustom) field.get(this);
			if(value != null && value.getChildren() != null){
				for(Node node:value.getChildren()){				
					if(node instanceof TextField){
						((TextField) node).setText("");
					}else if(node instanceof ComboBoxAutoComplete){
						((ComboBoxAutoComplete) node).setValue(null);
					}else if(node instanceof DatePickerField){
						((DatePickerField) node).setValue(null);
					}
				}
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void setNullFieldSpecial(java.lang.reflect.Field field){
		try {
			if(field.getType() == CheckBoxField.class){
			
				CheckBoxField value = (CheckBoxField) field.get(this);
				value.setSelected(false);
			}else if(field.getType() == PanelRangoFechas.class){
				PanelRangoFechas value = (PanelRangoFechas) field.get(this);
				value.getDprFechaDesde().getField().setValue(null);
				value.getDprFechaHasta().getField().setValue(null);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	protected byte[] imageFXtoByteArray(ImageView fxImagen){
		byte[] imagen = null;
		BufferedImage bImage = SwingFXUtils.fromFXImage(fxImagen.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "png", s);
			imagen = s.toByteArray();
			s.close(); //especially if you are using a different output stream.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagen;
	}	
	
	protected Image imageByteArrayToImageFx(byte[] imageByteArray){
		return new Image(new ByteArrayInputStream(imageByteArray));
	}				
	
	public static Date today(){
		return new java.sql.Timestamp(new Date().getTime());
	}
	
	public static String toDateFormat(Date fecha){
		return dateFormat.format(fecha);
	}
	
	public static String toDateFormatSQL(Date fecha){
		return dateFormatSQL.format(fecha);
	}
	
	public static String toTimeStampFormat(Date fecha){
		return timeStampFormat.format(fecha);
	}	
	
	public static int diffMonths(Date startDate, Date endDate){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);		
		return 1 + (diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
	}
	
	public static int diffYears(Date startDate, Date endDate){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);		
		return endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
	}
	
	public static Date firstDayOfMonth(){
		Calendar c = Calendar.getInstance();
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    return c.getTime();
	}
	
	public static Date lastDayOfMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));	
	    return c.getTime();
	}
	
	public static Image getImageSVG(InputStream path){
		BufferedImageTranscoder trans = new BufferedImageTranscoder();
		Image img = null;			
        TranscoderInput transIn = new TranscoderInput(path);
        try {
            trans.transcode(transIn, null);
            // Use WritableImage if you want to further modify the image (by using a PixelWriter)
            img = SwingFXUtils.toFXImage(trans.getBufferedImage(), null);             
        } catch (TranscoderException ex) {
            return null;
        }
      
		return img;
	}
}
