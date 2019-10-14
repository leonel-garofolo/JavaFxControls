package org.javafx.controls.viewpdf;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.FontPropertiesManager;
import org.icepdf.ri.util.PropertiesManager;

import javafx.embed.swing.SwingNode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * Example of integrating ICEpdf into a JavaFX application using Java 8 and SwingNode.
 */
public class PdfFXViewer extends StackPane {

   private SwingController swingController;
   private JComponent viewerPanel;   
   
   public PdfFXViewer(){
       createViewer(this);              
   }

   private void createViewer(StackPane stackPane) {
       try {
           SwingUtilities.invokeAndWait(() -> {
               // create the viewer ri components.
               swingController = new SwingControllerCustom();
               swingController.setIsEmbeddedComponent(true);
               PropertiesManager properties = new PropertiesManager(System.getProperties(),
                       ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));

               // read/store the font cache.
                ResourceBundle messageBundle = ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE);
                new FontPropertiesManager(properties, System.getProperties(), messageBundle);
                properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "1.25");
                properties.set(PropertiesManager.PROPERTY_SHOW_UTILITY_OPEN, "false");
                //properties.set(PropertiesManager.PROPERTY_SHOW_UTILITY_SAVE, "false");
                //properties.set(PropertiesManager.PROPERTY_SHOW_UTILITY_PRINT, "false");
                // hide the status bar
                properties.set(PropertiesManager.PROPERTY_SHOW_STATUSBAR, "false");
                // hide a few toolbars, just to show how the prefered size of the viewer changes.
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, "false");
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ROTATE, "false");
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_TOOL, "false");
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FORMS, "false");                
              
                swingController.getDocumentViewController().setAnnotationCallback(
                        new org.icepdf.ri.common.MyAnnotationCallback(swingController.getDocumentViewController()));

                SwingViewBuilder factory = new SwingViewBuilder(swingController, properties);                
                viewerPanel = factory.buildViewerPanel();
                viewerPanel.revalidate();              
                SwingNode swingNode = new SwingNode();
                swingNode.setContent(viewerPanel);               
                stackPane.getChildren().add(swingNode);
/*
                // add toolbar to the top.
                FlowPane toolBarFlow = new FlowPane();
                JToolBar mainToolbar = factory.buildCompleteToolBar(true);
                buildJToolBar(toolBarFlow, mainToolbar);
                borderPane.setTop(toolBarFlow);

                // main utility pane and viewer
                SwingNode swingNode = new SwingNode();
                viewerPanel = factory.buildUtilityAndDocumentSplitPane(true);
                swingNode.setContent(viewerPanel);
                borderPane.setCenter(swingNode);

                // the page view menubar
                FlowPane statusBarFlow = new FlowPane();
                buildButton(statusBarFlow, factory.buildPageViewSinglePageNonConToggleButton());
                buildButton(statusBarFlow, factory.buildPageViewSinglePageConToggleButton());
                buildButton(statusBarFlow, factory.buildPageViewFacingPageNonConToggleButton());
                buildButton(statusBarFlow, factory.buildPageViewFacingPageConToggleButton());
                borderPane.setBottom(statusBarFlow);
*/
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
	public void openDocument(String document) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	swingController.openDocument(document);
                viewerPanel.revalidate();
            }
        });

    }
    
    public void openDocument(byte[] data){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssSSS");

            	ByteArrayInputStream bis = new ByteArrayInputStream(data);            	
            	swingController.openDocument(bis, "tempPDF-" + dateFormat.format(new Date())+ ".pdf", 
            			System.getProperty("java.io.tmpdir") + "//");            	      
                viewerPanel.revalidate();
            }
        });

    }

    private void buildButton(FlowPane flowPane, AbstractButton jButton){
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(jButton);
        flowPane.getChildren().add(swingNode);
    }

    private void buildJToolBar(FlowPane flowPane, JToolBar jToolBar){
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(jToolBar);
        flowPane.getChildren().add(swingNode);
    }
}