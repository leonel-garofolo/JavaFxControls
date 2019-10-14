package org.javafx.controls.panels;

import org.javafx.controls.customs.DatePickerFieldVBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class PanelRangoFechas extends HBox implements  EventHandler<ActionEvent>{
	public static int PERIODO_DIA = 0;
	public static int PERIODO_SEMANA = 1;
	public static int PERIODO_MES = 2;
	public static int PERIODO_ANO = 3;		
	private String promptText = "dd/MM/yyyy";
		
	private DatePickerFieldVBox dprFechaDesde;
	private String labelDesde;
	private boolean requiredDesde;
		
	private DatePickerFieldVBox dprFechahasta;
	private String labelHasta;
	private boolean requiredHasta;
	
	public PanelRangoFechas(){
		super();
		initComponents();
	}
	
	private void initComponents() {				
		dprFechaDesde = new DatePickerFieldVBox();
		dprFechaDesde.setPromptText(promptText);
		dprFechaDesde.setLabel("Fecha Desde:");
		dprFechaDesde.setPrefWidth(110);		
		dprFechaDesde.getField().setOnAction(this);
		
		dprFechahasta = new DatePickerFieldVBox();
		dprFechahasta.setLabel("Fecha Hasta:");
		dprFechahasta.setPrefWidth(110);
		dprFechahasta.setPromptText(promptText);
		dprFechahasta.getField().setOnAction(this);
		
		getChildren().addAll(dprFechaDesde, dprFechahasta);		
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(dprFechaDesde.getField())){
			if(dprFechahasta.getLocalDate() != null &&  dprFechaDesde.getLocalDate() != null &&
					dprFechaDesde.getLocalDate().isAfter(dprFechahasta.getLocalDate())){
				dprFechahasta.setValue(dprFechaDesde.getValue());
			}
		}
		if(event.getSource().equals(dprFechahasta.getField())){
			if(dprFechaDesde.getLocalDate() != null && dprFechahasta.getLocalDate() != null &&
					dprFechahasta.getLocalDate().isBefore(dprFechaDesde.getLocalDate())){
				dprFechaDesde.setValue(dprFechahasta.getValue());
			}
		}		
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
	}

	public DatePickerFieldVBox getDprFechaDesde() {
		return dprFechaDesde;
	}

	public String getLabelDesde() {
		return labelDesde;
	}

	public void setLabelDesde(String labelDesde) {
		this.labelDesde = labelDesde;
		this.dprFechaDesde.setLabel(labelDesde);
	}

	public boolean isRequiredDesde() {
		return requiredDesde;
	}

	public void setRequiredDesde(boolean requiredDesde) {
		this.requiredDesde = requiredDesde;
		this.dprFechaDesde.setRequired(requiredDesde);
	}

	public DatePickerFieldVBox getDprFechaHasta() {
		return dprFechahasta;
	}	

	public String getLabelHasta() {
		return labelHasta;
	}

	public void setLabelHasta(String labelHasta) {
		this.labelHasta = labelHasta;
		this.dprFechahasta.setLabel(labelHasta);
	}

	public boolean isRequiredHasta() {
		return requiredHasta;
	}

	public void setRequiredHasta(boolean requiredHasta) {
		this.requiredHasta = requiredHasta;
		this.dprFechahasta.setRequired(requiredHasta);
	}

		
}
