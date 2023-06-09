package com.felixhua.codebook.ui.dialog;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SetContentDialog extends Dialog<ContentData> {
    ContentData contentData;

    private void initLayout() {
        setTitle(ResourceUtil.getMessage("set.title"));
        initOwner(MainController.getPrimaryStage());
        ButtonType submitButton = new ButtonType(ResourceUtil.getMessage("set.button.ok"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(ResourceUtil.getMessage("set.button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(cancelButton, submitButton);

        TextField titleField = new TextField(contentData.getTitle());
        TextField accountField = new TextField(contentData.getAccount());
        TextField passwordField = new TextField(contentData.getPassword());

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.addRow(0, new Label(ResourceUtil.getMessage("column.title")), titleField);
        formPane.addRow(1, new Label(ResourceUtil.getMessage("column.account")), accountField);
        formPane.addRow(2, new Label(ResourceUtil.getMessage("column.password")), passwordField);

        getDialogPane().setContent(formPane);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButton) {
                return new ContentData(titleField.getText(), accountField.getText(), passwordField.getText());
            }
            return null;
        });
    }

    public SetContentDialog(ContentData contentData) {
        this.contentData = contentData;
        initLayout();
    }
}
