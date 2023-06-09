package com.felixhua.codebook.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;

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

        // 调整滚动速度
        addEventFilter(ScrollEvent.SCROLL, event -> {
            double deltaY = event.getDeltaY();
            double scrollSpeed = 0.01;

            setVvalue(getVvalue() - deltaY * scrollSpeed);
            event.consume();
        });
    }
}
