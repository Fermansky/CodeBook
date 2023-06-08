package com.felixhua.codebook.entity;

import com.felixhua.codebook.constant.Constants;

public class ContentData {
    private String title;
    private String account;
    private String password;
    private String icon;

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public ContentData(String title, String account, String password) {
        this.title = title;
        this.account = account;
        this.password = password;
        this.icon = Constants.DEFAULT_ICON;
    }

    @Override
    public String toString() {
        return "{\"" + title + '\"' +
                ", \"" + account + '\"' +
                ", \"" + password + "\"}";
    }
}
