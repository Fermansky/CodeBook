package com.felixhua.codebook;

import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.ui.LoginPane;
import com.felixhua.codebook.util.FileUtil;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private Stage mainStage;
    private Scene scene;
    @Override
    public void start(Stage stage){
        mainStage = stage;
        scene = new Scene(new LoginPane());
        initStage();

        MainController.getInstance().setPrimaryStage(mainStage);
        mainStage.show();
        MainController.loadContentBook();
    }

    @Override
    public void stop() {
        FileUtil.writeCodeBook();
    }

    private void initStage() {
        mainStage.setScene(scene);
        mainStage.setHeight(800);
        mainStage.setWidth(500);
        mainStage.setMinHeight(550);
        mainStage.setMinWidth(350);
        mainStage.setTitle(ResourceUtil.getMessage("title"));
        mainStage.getIcons().add(new Image(Objects.requireNonNull(ResourceUtil.getImage("icon_200px.png"))));
    }
}
