package com.felixhua.codebook;

import com.felixhua.codebook.ui.LoginPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage mainStage;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        scene = new Scene(new LoginPane());
        initStage();

        mainStage.show();
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
