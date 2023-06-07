package com.felixhua.codebook.ui;

import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ResourceBundle;

public class AddContentDialog extends Dialog<ContentData> {

    private void initLayout() {
        setTitle(ResourceUtil.getMessage("add.title"));
        setHeaderText(ResourceUtil.getMessage("add.header"));
        ButtonType submitButton = new ButtonType(ResourceUtil.getMessage("add.button.ok"), ButtonBar.ButtonData.APPLY);
        ButtonType cancelButton = new ButtonType(ResourceUtil.getMessage("add.button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(cancelButton, submitButton);

        TextField titleField = new TextField();
        TextField accountField = new TextField();
        TextField passwordField = new TextField();

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.addRow(0, new Label(ResourceUtil.getMessage("add.column.title")), titleField);
        formPane.addRow(1, new Label(ResourceUtil.getMessage("add.column.account")), accountField);
        formPane.addRow(2, new Label(ResourceUtil.getMessage("add.column.password")), passwordField);

        getDialogPane().setContent(formPane);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButton) {
                return new ContentData(titleField.getText(), accountField.getText(), passwordField.getText());
            }
            return null;
        });
    }
    public AddContentDialog() {
        initLayout();
    }
}
