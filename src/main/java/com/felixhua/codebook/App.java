package com.felixhua.codebook;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.ui.LoginPane;
import com.felixhua.codebook.util.FileUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    private Stage mainStage;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        scene = new Scene(new LoginPane());
        initStage();
        FileUtil.loadFile();
        MainController.getInstance().setPrimaryStage(mainStage);

        mainStage.show();
    }

    @Override
    public void stop() throws IOException {
        FileUtil.writeFile();
    }

    private void initStage() {
        mainStage.setScene(scene);
        mainStage.setHeight(800);
        mainStage.setWidth(500);
        mainStage.setMinHeight(550);
        mainStage.setMinWidth(350);
        mainStage.setTitle("Code Book");
    }
}
