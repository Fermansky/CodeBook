package com.felixhua.codebook.controller;

import com.felixhua.codebook.ui.LoginPane;

public class LoginController {
    private static final LoginController loginController = new LoginController();
    private String password = "114514";
    public static LoginController getInstance() {
        return loginController;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String password) {
        return (password.equals(MainController.getCurrentCodeBook().getPassword()));
    }
    private LoginController() {

    }
}
