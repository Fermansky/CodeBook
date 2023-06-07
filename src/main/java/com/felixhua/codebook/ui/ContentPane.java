package com.felixhua.codebook.ui;

import javafx.scene.layout.VBox;

public class ContentPane extends VBox {
    public static final ContentPane contentPane = new ContentPane();

    public static ContentPane getInstance() {
        return contentPane;
    }

    public static void addCell(ContentCell contentCell) {
        contentPane.getChildren().add(contentCell);
    }

    private void initLayout() {

    }

    private ContentPane() {
        initLayout();
    }
}
