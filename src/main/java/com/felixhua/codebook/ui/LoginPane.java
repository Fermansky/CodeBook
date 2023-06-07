package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoginPane extends BorderPane {
    private VBox centralVBox;
    private PasswordField passwordField;

    private void initCentralVBox() {
        centralVBox = new VBox();
        centralVBox.setAlignment(Pos.CENTER);
        centralVBox.setSpacing(20);
        centralVBox.setMaxSize(300, 500);
        centralVBox.setMinSize(300, 500);
        centralVBox.setTranslateY(-50);
        ImageView logoView = new ImageView();
        Image logoImage = new Image(ResourceUtil.getImage("codebook-icon-300px.png"));
        logoView.setImage(logoImage);
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        Label label = new Label("Welcome to Code Book, please Enter the Password:");
        passwordField = new PasswordField();
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                submit();
            }
        });
        Button submitButton = new Button("Login");
        submitButton.setOnMousePressed(mouseEvent -> {
            submit();
        });
        centralVBox.getChildren().addAll(logoView, label, passwordField, submitButton);
    }
    private void initLayout() {
        initCentralVBox();
        setCenter(centralVBox);
        getStylesheets().add("css/login-pane.css");
    }

    private void submit() {
        boolean login = LoginController.getInstance().login(passwordField.getText());
        System.out.println(login);
    }

    public LoginPane() {
        initLayout();
    }
}
