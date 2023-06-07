package com.felixhua.codebook.ui;

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

public class ResetPane extends BorderPane {
    private static final ResetPane resetPane = new ResetPane();
    private VBox centralVBox;
    private Label tipLabel;
    private PasswordField passwordField;
    private Button submitButton;
    public static ResetPane getInstance() {
        return resetPane;
    }

    private void initCentralVBox() {
        centralVBox = new VBox();
        centralVBox.setAlignment(Pos.CENTER);
        centralVBox.setSpacing(20);
        centralVBox.setMaxSize(300, 500);
        centralVBox.setMinSize(300, 500);
        ImageView logoView = new ImageView();
        Image logoImage = new Image(ResourceUtil.getImage("codebook-icon-300px.png"));
        logoView.setImage(logoImage);
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        Label label = new Label("To reset your password, please enter your old one:");
        tipLabel = new Label();
        passwordField = new PasswordField();
        passwordField.setPrefSize(300, 25);
        passwordField.setFont(new Font(20));
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                submit();
            }
        });
        submitButton = new Button("Next Step");
        submitButton.setPrefSize(200, 50);
        submitButton.setFont(Font.font(20));
        submitButton.setOnMousePressed(mouseEvent -> {
            submit();
        });

        centralVBox.getChildren().addAll(logoView, label, passwordField, submitButton, tipLabel);
    }

    private void submit() {

    }

    private void initLayout() {
        initCentralVBox();

        setCenter(centralVBox);
    }

    private ResetPane() {
        initLayout();
    }
}
