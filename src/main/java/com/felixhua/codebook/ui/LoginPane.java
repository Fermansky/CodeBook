package com.felixhua.codebook.ui;

import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginPane extends BorderPane {
    private VBox centralVBox;
    private PasswordField passwordField;
    private Label tipLabel;
    private Button submitButton;

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
        tipLabel = new Label();
        passwordField = new PasswordField();
        passwordField.setPrefSize(300, 25);
        passwordField.setFont(new Font(20));
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                submit();
            }
        });
        submitButton = new Button("Login");
        submitButton.setPrefSize(200, 50);
        submitButton.setFont(Font.font(20));
        submitButton.setOnMousePressed(mouseEvent -> {
            submit();
        });
        centralVBox.getChildren().addAll(logoView, label, passwordField, submitButton, tipLabel);
    }
    private void initLayout() {
        initCentralVBox();
        setCenter(centralVBox);
        getStylesheets().add("css/login-pane.css");
    }

    private void submit() {
        boolean login = LoginController.getInstance().login(passwordField.getText());
        if (login) {
            tipLabel.setText("Login success.");
            MainController.getInstance().getPrimaryStage().getScene().setRoot(ContentPane.getInstance());
        } else {
            tipLabel.setText("Login failed.");
        }
    }

    public LoginPane() {
        initLayout();
    }
}
