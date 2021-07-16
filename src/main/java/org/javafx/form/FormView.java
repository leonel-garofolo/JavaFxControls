package org.javafx.form;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.javafx.form.model.FormField;

import java.util.ArrayList;
import java.util.List;

public class FormView extends VBox {
    protected List<FormField> fields;
    protected FormBuilder formBuilder;
    protected VBox screen;
    protected final HBox hboxBottom;
    public FormView(String title) {
        super();
        this.hboxBottom = new HBox();
        this.fields = new ArrayList<>();
        this.formBuilder = new FormBuilder(title, fields);

        initialize();
    }

    private void initialize() {
        this.hboxBottom.setId("BoxButton");
        this.hboxBottom.setSpacing(10);
        this.hboxBottom.setPadding(new Insets(5, 10, 5, 0));

        this.screen = new VBox();
        ScrollPane scroll = new ScrollPane(screen);
        scroll.setContent(screen);
        scroll.setMaxHeight(Double.MAX_VALUE);
        getChildren().add(scroll);
        getChildren().add(hboxBottom);
    }
}
