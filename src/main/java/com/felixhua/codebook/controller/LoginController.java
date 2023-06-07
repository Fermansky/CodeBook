package com.felixhua.codebook.controller;

public class LoginController {
    private static final LoginController loginController = new LoginController();
    private final String password = "114514";
    public static LoginController getInstance() {
        return loginController;
    }

    public boolean login(String password) {
        return (this.password.equals(password));
    }
    private LoginController() {

    }
}
