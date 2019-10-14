package org.javafx.controls.viewpdf;

import java.awt.print.PrinterException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.icepdf.ri.common.SwingController;

import javafx.application.Platform;
import javafx.print.PrinterJob;
import javafx.stage.FileChooser;

public class SwingControllerCustom extends SwingController{	
	
	@Override
	public void saveFile(){		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	FileChooser fileChooser = new FileChooser();
	  		  
	            //Set extension filter
	            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	            fileChooser.getExtensionFilters().add(extFilter);
	            
	            //Show save file dialog
	            //File file = fileChooser.showSaveDialog(((Stage)father.getScene().getWindow()));
	            File file = fileChooser.showSaveDialog(null);
	            
	            if(file != null){
	            	FileOutputStream fileOutputStream;
					try {
						fileOutputStream = new FileOutputStream(file);
						BufferedOutputStream buf = new BufferedOutputStream(fileOutputStream, 8192);					
						getDocument().saveToOutputStream(buf);
						buf.flush();
						fileOutputStream.flush();
						buf.close();
						fileOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				
	            }
	        }
	   }); 
	}
	
	@Override
	public void print(boolean print){
		// Create the PrinterJob		
		PrinterJob job = PrinterJob.createPrinterJob();	
		if (job == null) 
		{
			return;
		}
		
		// Show the print setup dialog
		boolean proceed = job.showPrintDialog(null);
		if (proceed){
			PrintService[] pservices = PrintServiceLookup.lookupPrintServices(null, null);
			PrintService printer = null;
	        for (PrintService serv: pservices) {
	           if (serv.getName().equals(job.getPrinter().getName())) {
	                printer = serv;
	            }
	        }
	        
			java.awt.print.PrinterJob printerJob = java.awt.print.PrinterJob.getPrinterJob();
			try {
				printerJob.setPrintService(printer);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PDDocument pDDocument;
			try {
				pDDocument = PDDocument.load(new File(getDocument().getDocumentOrigin()));
				printerJob.setPageable(new PDFPageable(pDDocument));	        			     
				printerJob.print();
				pDDocument.close();		        
			} catch (PrinterException | IOException e) {				
				e.printStackTrace();
			}
		}
	}	 
}
