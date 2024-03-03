package com.felixhua.codebook.ui;

import com.felixhua.codebook.entity.ContentData;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;

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

    public static void reload(List<ContentData> contentDataList) {
        contentPane.getChildren().clear();
        for(ContentData contentData : contentDataList) {
            ContentCell contentCell = contentData.getContentCell();
            if (contentCell == null) {
                contentCell = new ContentCell(contentData);
            }
            contentPane.getChildren().add(contentCell);
        }
    }

    private void initLayout() {

    }

    private ContentPane() {
        initLayout();
    }
}
