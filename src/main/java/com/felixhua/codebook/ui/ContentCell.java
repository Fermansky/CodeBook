package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Optional;

public class ContentCell extends HBox {
    private ContentData contentData;
    private ImageView iconView;
    private VBox infoWrapper;
    private Label titleLabel;
    private Label accountLabel;
    private Label passwordLabel;

    public void setContentData(ContentData contentData) {
        this.contentData = contentData;
    }

    public ContentData getContentData() {
        return this.contentData;
    }

    /**
     * 初始化布局，仅在本对象创建时调用！
     */
    private void initLayout() {
        getStylesheets().add("css/content-cell.css");
        getStyleClass().add("root");
        iconView = new ImageView(contentData.getIcon());
        iconView.setPreserveRatio(true);
        iconView.setFitWidth(60);

        titleLabel = new Label(contentData.getTitle());
        titleLabel.setFont(Font.font(25));
        titleLabel.setPadding(new Insets(0, 0, 3, 0));
        accountLabel = new Label("账号: " + contentData.getAccount());
        passwordLabel = new Label("密码: " + contentData.getPassword());
        BorderPane deleteButton = new BorderPane();
        deleteButton.getStyleClass().add("delete-btn");
        deleteButton.setMinWidth(50);
        Region deleteRegion = new Region();
        deleteRegion.setMinSize(25, 25);
        deleteRegion.setMaxSize(25, 25);
        deleteButton.setCenter(deleteRegion);

        infoWrapper = new VBox(titleLabel, accountLabel, passwordLabel);
        getChildren().addAll(iconView, infoWrapper, deleteButton);
        deleteButton.setOnMouseClicked(mouseEvent -> {
            mouseEvent.consume();
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle(ResourceUtil.getMessage("delete.confirm.title"));
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText(ResourceUtil.getMessage("delete.confirm.text"));
            ButtonType confirmButton = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationDialog.getButtonTypes().setAll(confirmButton, cancelButton);

            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent() && result.get() == confirmButton) {
                delete();
            }
        });

        HBox.setHgrow(infoWrapper, Priority.ALWAYS);
        prefWidthProperty().bind(ContentWrapper.getInstance().prefViewportWidthProperty());

        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
    }

    private void delete() {
        MainController.removeContentData(contentData);
    }

    public void refresh() {
        this.titleLabel.setText(contentData.getTitle());
        this.accountLabel.setText("账号: " + contentData.getAccount());
        this.passwordLabel.setText("密码: " + contentData.getPassword());
    }

    public ContentCell(ContentData contentData) {
        this.contentData = contentData;
        initLayout();
        setOnMouseClicked(mouseEvent -> {
            Optional<ContentData> contentData1 = new SetContentDialog(contentData).showAndWait();
            contentData1.ifPresent(data -> {
                this.contentData.setTitle(data.getTitle());
                this.contentData.setAccount(data.getAccount());
                this.contentData.setPassword(data.getPassword());
                this.refresh();
            });
        });
        contentData.setContentCell(this);
    }
}
