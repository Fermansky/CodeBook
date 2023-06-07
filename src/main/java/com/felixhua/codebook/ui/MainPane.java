package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class MainPane extends BorderPane {
    private static final MainPane MAIN_PANE = new MainPane();
    private ScrollPane contentWrapper;
    private BorderPane topPane;
    private BorderPane bottomPane;
    private TextField searchField;
    private Button searchButton;
    private Label tipLabel;
    public static MainPane getInstance() {
        return MAIN_PANE;
    }

    private void initContentWrapper() {
        this.contentWrapper = new ScrollPane(ContentPane.getInstance());
        contentWrapper.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    private void initTopPane() {
        topPane = new BorderPane();
        searchField = new TextField();
        searchField.setPromptText(ResourceUtil.getMessage("search.placeholder"));
        searchButton = new Button(ResourceUtil.getMessage("search.button"));

        HBox searchWrapper = new HBox(searchField, searchButton);
        searchWrapper.getStyleClass().add("search-wrapper");
        searchWrapper.maxWidthProperty().bind(MainController.getInstance().getPrimaryStage().widthProperty().divide(2));
        searchField.prefWidthProperty().bind(searchWrapper.widthProperty().subtract(50));
        searchWrapper.setSpacing(10);
        searchWrapper.setAlignment(Pos.CENTER);

        topPane.setCenter(searchWrapper);
        topPane.setPrefHeight(50);
    }

    private void initLayout() {
        getStylesheets().add("css/main-pane.css");
        initContentWrapper();
        initTopPane();
        tipLabel = new Label(ResourceUtil.getMessage("main.tip.ready"));
        bottomPane = new BorderPane();
        bottomPane.setPadding(new Insets(0, 0, 0, 2));
        bottomPane.setLeft(tipLabel);

        setTop(topPane);
        setBottom(bottomPane);
        setCenter(contentWrapper);
    }
    private MainPane() {
        initLayout();
    }
}
