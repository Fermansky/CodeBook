package com.felixhua.codebook.ui;

import com.felixhua.codebook.entity.ContentData;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ContentCell extends HBox {
    private ContentData contentData;
    private ImageView iconView;
    private VBox infoWrapper;
    private Label applicationLabel;
    private Label accountLabel;
    private Label passwordLabel;

    public void setContentData(ContentData contentData) {
        this.contentData = contentData;
    }

    private void initLayout() {
        getStylesheets().add("css/content-cell.css");
        getStyleClass().add("root");
        iconView = new ImageView(contentData.getIcon());
        iconView.setPreserveRatio(true);
        iconView.setFitWidth(80);

        applicationLabel = new Label(contentData.getTitle());
        applicationLabel.setFont(Font.font(25));
        applicationLabel.setPadding(new Insets(5, 0, 5, 0));
        accountLabel = new Label("账号: " + contentData.getAccount());
        passwordLabel = new Label("密码: " + contentData.getPassword());

        infoWrapper = new VBox(applicationLabel, accountLabel, passwordLabel);
        getChildren().addAll(iconView, infoWrapper);

        prefWidthProperty().bind(ContentWrapper.getInstance().prefViewportWidthProperty());

        setSpacing(20);
        setPadding(new Insets(5, 5, 5, 5));
    }
    public ContentCell(ContentData contentData) {
        this.contentData = contentData;
        initLayout();
    }
}
