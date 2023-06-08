package com.felixhua.codebook.ui;

import com.felixhua.codebook.entity.ContentData;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ContentPane extends VBox {
    public static final ContentPane contentPane = new ContentPane();

    public static ContentPane getInstance() {
        return contentPane;
    }

    public static void addCell(ContentCell contentCell) {
        contentPane.getChildren().add(contentCell);
    }

    public static void removeCell(ContentData contentData) {
        for (Node contentCell : contentPane.getChildren()) {
            if (contentCell instanceof ContentCell) {
                if(((ContentCell) contentCell).getContentData() == contentData) {
                    contentPane.getChildren().remove(contentCell);
                    return ;
                }
            }
        }
    }

    private void initLayout() {

    }

    private ContentPane() {
        initLayout();
    }
}
