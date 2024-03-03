package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.controller.SearchController;
import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.ui.dialog.AddContentDialog;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class MainPane extends BorderPane {
    private static final MainPane MAIN_PANE = new MainPane();
    private ContentWrapper contentWrapper;
    private BorderPane topPane;
    private BorderPane bottomPane;
    private TextField searchField;
    private Button searchButton;
    private Label tipLabel;
    private Button addCellButton;
    private Button settingButton;
    public static MainPane getInstance() {
        return MAIN_PANE;
    }

    private void initContentWrapper() {
        this.contentWrapper = ContentWrapper.getInstance();
    }

    private void initTopPane() {
        topPane = new BorderPane();
        searchField = new TextField();
        searchField.setPromptText(ResourceUtil.getMessage("search.placeholder"));
        searchButton = new Button(ResourceUtil.getMessage("search.button"));
        searchButton.setOnMousePressed(mouseEvent -> search());
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                search();
            }
        });

        HBox searchWrapper = new HBox(searchField, searchButton);
        searchWrapper.getStyleClass().add("search-wrapper");
        searchWrapper.maxWidthProperty().bind(MainController.getPrimaryStage().widthProperty().subtract(150));
        searchField.prefWidthProperty().bind(searchWrapper.widthProperty().subtract(100));
        searchField.setMinWidth(180);
        searchWrapper.setSpacing(10);
        searchWrapper.setMinWidth(250);
        searchWrapper.setAlignment(Pos.CENTER);

        addCellButton = new Button("+");
        addCellButton.setOnMousePressed(mouseEvent -> {
            showAddContentDialog();
        });
        BorderPane addCellWrapper = new BorderPane();
        addCellWrapper.setCenter(addCellButton);
        addCellWrapper.setPadding(new Insets(5, 5, 5, 5));

        settingButton = new Button("设置");
        settingButton.setOnMousePressed(mouseEvent -> {

        });
        BorderPane settingWrapper = new BorderPane();
        settingWrapper.setCenter(settingButton);
        settingWrapper.setPadding(new Insets(5, 5, 5, 5));

        topPane.setCenter(searchWrapper);
        topPane.setRight(addCellWrapper);
        topPane.setLeft(settingWrapper);
        topPane.setPrefHeight(50);
    }

    private void initLayout() {
        getStylesheets().add("css/main-pane.css");
        initContentWrapper();
        initTopPane();
        tipLabel = new Label(ResourceUtil.getMessage("main.tip.ready"));
        bottomPane = new BorderPane();
        bottomPane.setPadding(new Insets(2, 2, 2, 2));
        bottomPane.setLeft(tipLabel);

        setTop(topPane);
        setBottom(bottomPane);
        setCenter(contentWrapper);
    }

    private void showAddContentDialog() {
        AddContentDialog addContentDialog = new AddContentDialog();
        Optional<ContentData> contentData = addContentDialog.showAndWait();
        contentData.ifPresent(MainController::addContentData);
    }

    private void search() {
        ContentPane.reload(SearchController.search(searchField.getText()));
    }

    private MainPane() {
        initLayout();
    }
}
