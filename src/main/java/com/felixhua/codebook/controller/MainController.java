package com.felixhua.codebook.controller;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainController {
    private static final MainController mainController = new MainController();
    private Stage primaryStage;

    public static MainController getInstance() {
        return mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setStageContent(Parent content) {
        this.primaryStage.getScene().setRoot(content);
    }

    private MainController() {

    }
}
