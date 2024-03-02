package com.felixhua.codebook.controller;

import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.ui.ContentCell;
import com.felixhua.codebook.ui.ContentPane;
import com.felixhua.codebook.ui.LoginPane;
import com.felixhua.codebook.ui.MainPane;
import com.felixhua.codebook.util.FileUtil;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {
    private static final MainController mainController = new MainController();
    private static Stage primaryStage;
    private static ArrayList<ContentData> contentDataList;

    public static MainController getInstance() {
        return mainController;
    }

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setStageContent(Parent content) {
        primaryStage.getScene().setRoot(content);
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

    public static void loadContentBook() {
        String s = FileUtil.loadCodeBook();
        LoginPane.disable(s != null);
    }

    private MainController() {
        contentDataList = new ArrayList<>();
    }
}
