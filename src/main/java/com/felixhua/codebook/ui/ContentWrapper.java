package com.felixhua.codebook.ui;

import javafx.scene.control.ScrollPane;

public class ContentWrapper extends ScrollPane {
    private static final ContentWrapper contentWrapper = new ContentWrapper();

    public static ContentWrapper getInstance() {
        return contentWrapper;
    }

    private void initLayout() {
        setFitToWidth(true);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

    private ContentWrapper() {
        setContent(ContentPane.getInstance());
        initLayout();
    }
}
