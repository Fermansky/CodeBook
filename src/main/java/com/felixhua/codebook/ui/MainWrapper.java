package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.util.FileUtil;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

/**
 * 用于包装主页面和上端目录条
 */
public class MainWrapper extends BorderPane {
    private static MainWrapper mainWrapper = new MainWrapper();

    public static MainWrapper getInstance() {
        return mainWrapper;
    }

    private void export() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("导出数据");
        alert.setHeaderText(null);
        alert.setContentText("确定要导出数据吗？如果确定，请妥善保存好导出的数据。");
        alert.initOwner(MainController.getPrimaryStage());
        ButtonType confirm = new ButtonType("确认", ButtonBar.ButtonData.YES);
        ButtonType cancel = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirm, cancel);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.isPresent() && buttonType.get() == confirm) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv文件", ".csv"));
            fileChooser.setInitialFileName("data");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents"));
            File file = fileChooser.showSaveDialog(MainController.getPrimaryStage());
            if (file != null) {
                FileUtil.exportCSV(file);
            }
        }
    }

    private MainWrapper() {
        setCenter(MainPane.getInstance());
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu(ResourceUtil.getMessage("menu.file"));
        MenuItem export = new MenuItem(ResourceUtil.getMessage("menu.file.export"));
        export.setOnAction(e -> {
            export();
        });
        file.getItems().add(export);
        menuBar.getMenus().add(file);
        setTop(menuBar);
    }
}
