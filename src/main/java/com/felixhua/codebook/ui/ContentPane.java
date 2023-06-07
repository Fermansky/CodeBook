package com.felixhua.codebook.ui;

import javafx.scene.layout.Pane;

public class ContentPane extends Pane {
    public static final ContentPane contentPane = new ContentPane();

    public static ContentPane getInstance() {
        return contentPane;
    }

    private ContentPane() {

    }
}
