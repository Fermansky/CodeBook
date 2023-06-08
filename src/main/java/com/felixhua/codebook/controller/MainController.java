package com.felixhua.codebook.controller;

import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.ui.ContentCell;
import com.felixhua.codebook.ui.ContentPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {
    private static final MainController mainController = new MainController();
    private Stage primaryStage;
    private static ArrayList<ContentData> contentDataList;

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

    public static ArrayList<ContentData> getContentDataList() {
        return contentDataList;
    }

    public static void addContentData(ContentData contentData) {
        contentDataList.add(contentData);
        ContentPane.addCell(new ContentCell(contentData));
    }

    public static void removeContentData(ContentData contentData) {
        contentDataList.remove(contentData);
        ContentPane.removeCell(contentData);
    }

    private MainController() {
        contentDataList = new ArrayList<>();
    }
}
