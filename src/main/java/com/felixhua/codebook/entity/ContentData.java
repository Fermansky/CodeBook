package com.felixhua.codebook.entity;

import com.felixhua.codebook.constant.Constants;
import javafx.scene.image.Image;

public class ContentData {
    private String application;
    private String account;
    private String password;
    private String icon;

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    public String getApplication() {
        return application;
    }

    public String getIcon() {
        return icon;
    }

    public ContentData(String application, String account, String password) {
        this.application = application;
        this.account = account;
        this.password = password;
        this.icon = Constants.DEFAULT_ICON;
    }
}
