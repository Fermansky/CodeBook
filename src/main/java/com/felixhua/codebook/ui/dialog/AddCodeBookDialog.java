package com.felixhua.codebook.ui.dialog;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.CodeBook;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;

public class AddCodeBookDialog extends Dialog<CodeBook> {
    private void initLayout() {
        setTitle(ResourceUtil.getMessage("add-cb.title"));
        setHeaderText(ResourceUtil.getMessage("add-cb.header"));
        initOwner(MainController.getPrimaryStage());
        ButtonType submitButton = new ButtonType(ResourceUtil.getMessage("add-cb.button.ok"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(ResourceUtil.getMessage("add-cb.button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(cancelButton, submitButton);

        TextField titleField = new TextField(ResourceUtil.getMessage("add-cb.new.name"));
        TextField passwordField = new TextField();

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.addRow(0, new Label(ResourceUtil.getMessage("column.title")), titleField);
        formPane.addRow(1, new Label(ResourceUtil.getMessage("column.password")), passwordField);

        getDialogPane().setContent(formPane);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButton) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("选择保存位置");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("cb文件", "*.cb"));
                fileChooser.setInitialFileName(titleField.getText());
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents"));
                File file = fileChooser.showSaveDialog(this.getOwner());
                CodeBook codeBook = new CodeBook(file);
                codeBook.setPassword(passwordField.getText());
                return codeBook;
            }
            return null;
        });
    }
    public AddCodeBookDialog() {
        initLayout();
    }
}
