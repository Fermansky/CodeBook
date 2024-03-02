package com.felixhua.codebook.ui;

import com.felixhua.codebook.constant.Constants;
import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.CodeBook;
import com.felixhua.codebook.entity.Configuration;
import com.felixhua.codebook.ui.dialog.AddCodeBookDialog;
import com.felixhua.codebook.util.DesktopUtil;
import com.felixhua.codebook.util.FileUtil;
import com.felixhua.codebook.util.ResourceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class LoginPane extends BorderPane {
    private static final LoginPane loginPane = new LoginPane();
    private VBox centralVBox;
    private static PasswordField passwordField;
    private Label tipLabel;
    private Label resetLabel;
    private Label selectLabel;
    private Label codebookLabel;

    private HBox selectBox;
    private static Button submitButton;
    private static Button addButton;

    public static LoginPane getInstance() {
        return loginPane;
    }

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

        selectBox = new HBox();
        selectBox.setAlignment(Pos.CENTER);
        selectBox.setSpacing(10);
        addButton = new Button("+");
        addButton.setTooltip(new Tooltip("新建密码簿"));
        addButton.setOnAction(event -> {
            Optional<CodeBook> codeBook = new AddCodeBookDialog().showAndWait();
            codeBook.ifPresent(Configuration::addCodeBook);
        });
        selectLabel = new Label(ResourceUtil.getMessage("login.select"));
        codebookLabel = new Label(FileUtil.file.getName());
        codebookLabel.setTextFill(Color.BLUE);
        codebookLabel.setTooltip(new Tooltip(FileUtil.file.getAbsolutePath()));
        codebookLabel.setCursor(Cursor.HAND);
        codebookLabel.setOnMousePressed(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(FileUtil.file.getParentFile());
            fileChooser.setTitle("选择密码簿");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("密码簿文件", "*.cb"));
            File file = fileChooser.showOpenDialog(MainController.getPrimaryStage());
            FileUtil.importCodeBook(file);
        });
        ChoiceBox<CodeBook> cb = new ChoiceBox<>();
        for (CodeBook codeBook : Configuration.getCodeBookList()) {
            cb.getItems().add(codeBook);
            if (cb.getValue() == null) {
                cb.setValue(codeBook);
                FileUtil.loadCodeBook(codeBook);
            }
        }

        cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            FileUtil.loadCodeBook(newValue);
        });

        selectBox.getChildren().addAll(selectLabel, cb, addButton);

        tipLabel = new Label();
        passwordField = new PasswordField();
        passwordField.setPrefSize(300, 25);
        passwordField.setFont(new Font(20));
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                submitButton.requestFocus();
                submit();
            }
        });
        submitButton = new Button(ResourceUtil.getMessage("login.button"));
        submitButton.setPrefSize(200, 50);
        submitButton.setFont(Font.font(20));
        submitButton.setOnMousePressed(mouseEvent -> {
            submit();
        });
        centralVBox.getChildren().addAll(logoView, label, selectBox, passwordField, submitButton, tipLabel);
    }
    private void initLayout() {
        initCentralVBox();
        passwordField.requestFocus();
        setCenter(centralVBox);
        getStylesheets().add("css/login-pane.css");
    }

    private void submit() {
        boolean login = LoginController.getInstance().login(passwordField.getText());
        if (login) {
            tipLabel.setText(ResourceUtil.getMessage("login.tip.success"));
            MainController.setStageContent(MainWrapper.getInstance());
        } else {
            tipLabel.setText(ResourceUtil.getMessage("login.tip.failure"));
        }
    }

    public static void disable(boolean disable) {
        passwordField.setDisable(disable);
        submitButton.setDisable(disable);
    }

    private LoginPane() {
        initLayout();
    }

}
