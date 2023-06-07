package com.felixhua.codebook.ui;

import com.felixhua.codebook.constant.Constants;
import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.util.DesktopUtil;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class LoginPane extends BorderPane {
    private VBox centralVBox;
    private PasswordField passwordField;
    private Label tipLabel;
    private Label resetLabel;
    private Button submitButton;

    private void initCentralVBox() {
        centralVBox = new VBox();
        centralVBox.setAlignment(Pos.CENTER);
        centralVBox.setSpacing(20);
        centralVBox.setMaxSize(300, 500);
        centralVBox.setMinSize(300, 500);
        ImageView logoView = new ImageView();
        Image logoImage = new Image(Objects.requireNonNull(ResourceUtil.getLocalizedImage("codebook-icon-300px.png")));
        logoView.setImage(logoImage);
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        logoView.setOnMouseClicked(mouseEvent -> {
            DesktopUtil.browse(Constants.PROJECT_URL);
        });
        logoView.setCursor(Cursor.HAND);
        Label label = new Label(ResourceUtil.getMessage("login.welcome"));
        tipLabel = new Label();
        passwordField = new PasswordField();
        passwordField.setPrefSize(300, 25);
        passwordField.setFont(new Font(20));
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                submit();
            }
        });
        submitButton = new Button(ResourceUtil.getMessage("login.button"));
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
        resetLabel = new Label(ResourceUtil.getMessage("login.reset"));
        resetLabel.getStyleClass().add("hyperlink");
        resetLabel.setAlignment(Pos.CENTER);
        resetLabel.setOnMouseClicked(mouseEvent -> {
            MainController.getInstance().setStageContent(ResetPane.getInstance());
        });
        setBottom(resetLabel);
        getStylesheets().add("css/login-pane.css");
    }

    private void submit() {
        boolean login = LoginController.getInstance().login(passwordField.getText());
        if (login) {
            tipLabel.setText(ResourceUtil.getMessage("login.tip.success"));
            MainController.getInstance().setStageContent(MainPane.getInstance());
        } else {
            tipLabel.setText(ResourceUtil.getMessage("login.tip.failure"));
        }
    }

    public LoginPane() {
        initLayout();
    }
}
