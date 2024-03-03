package com.felixhua.codebook.controller;

import com.felixhua.codebook.entity.CodeBook;
import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.ui.ContentCell;
import com.felixhua.codebook.ui.ContentPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final MainController mainController = new MainController();
    private static Stage primaryStage;
    private static List<ContentData> contentDataList;
    private static CodeBook currentCodeBook;

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

    public static List<ContentData> getContentDataList() {
        return contentDataList;
    }

    public static void setCurrentCodeBook(CodeBook currentCodeBook) {
        MainController.currentCodeBook = currentCodeBook;
        loadContentBook();
    }

    public static CodeBook getCurrentCodeBook() {
        return currentCodeBook;
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
        contentDataList = currentCodeBook.getContentDataList();
        ContentPane.reload(contentDataList);
    }

    private MainController() {
        contentDataList = new ArrayList<>();
    }
}
