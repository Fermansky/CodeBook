package com.felixhua.codebook.ui;

import com.felixhua.codebook.constant.Constants;
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
    private static final MainWrapper mainWrapper = new MainWrapper();
    private static MenuBar menuBar;

    public static MainWrapper getInstance() {
        return mainWrapper;
    }

    private void importData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv文件", "*.csv"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents"));
        File file = fileChooser.showOpenDialog(MainController.getPrimaryStage());
        if (file != null) {
            FileUtil.importCSV(file);
        }
    }

    private void exportData() {
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
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv文件", "*.csv"));
            fileChooser.setInitialFileName("data");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents"));
            File file = fileChooser.showSaveDialog(MainController.getPrimaryStage());
            if (file != null) {
                FileUtil.exportCSV(file);
                Alert informDialog = new Alert(Alert.AlertType.INFORMATION);
                informDialog.setTitle("导出成功");
                informDialog.setHeaderText(null);
                informDialog.setContentText("数据已成功导出！");
                informDialog.initOwner(MainController.getPrimaryStage());
                informDialog.showAndWait();
            }
        }
    }

    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setSelectedExtensionFilter(Constants.CODE_BOOK_EXTENSION_FILTER);
        fileChooser.getExtensionFilters().add(Constants.CODE_BOOK_EXTENSION_FILTER);
        File file = fileChooser.showSaveDialog(MainController.getPrimaryStage());
        if(file != null) {
            FileUtil.writeCodeBook(file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(MainController.getPrimaryStage());
            alert.setTitle(ResourceUtil.getMessage("menu.file.save-as.success.title"));
            alert.setHeaderText(null);
            alert.setContentText(ResourceUtil.getMessage("menu.file.save-as.success.text"));
            alert.showAndWait();
        }
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        Menu file = new Menu(ResourceUtil.getMessage("menu.file"));
        MenuItem exportData = new MenuItem(ResourceUtil.getMessage("menu.file.export"));
        exportData.setOnAction(e -> exportData());
        MenuItem importData = new MenuItem(ResourceUtil.getMessage("menu.file.import"));
        importData.setOnAction(e -> importData());
        MenuItem open = new MenuItem(ResourceUtil.getMessage("menu.file.open"));
        MenuItem saveAs = new MenuItem(ResourceUtil.getMessage("menu.file.save-as"));
        saveAs.setOnAction(e -> saveAs());
        file.getItems().addAll(open, saveAs, new SeparatorMenuItem(), importData, exportData);
        menuBar.getMenus().add(file);
    }

    private void initLayout() {
        initMenuBar();
        setCenter(MainPane.getInstance());
        setTop(menuBar);
    }

    private MainWrapper() {
        initLayout();
    }
}
