package com.felixhua.codebook.entity;

import com.felixhua.codebook.constant.Constants;
import com.felixhua.codebook.ui.ContentCell;

import java.util.Arrays;
import java.util.List;

public class ContentData {
    private String title;
    private String account;
    private String password;
    private String icon;
    private ContentCell contentCell;

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

    public ContentCell getContentCell() {
        return contentCell;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContentCell(ContentCell contentCell) {
        this.contentCell = contentCell;
    }

    public ContentData(String title, String account, String password) {
        this.title = title;
        this.account = account;
        this.password = password;
        this.icon = Constants.DEFAULT_ICON;
    }

    public List<String> toStringList() {
        return Arrays.asList(title, account, password);
    }

    @Override
    public String toString() {
        return "{\"" + title + '\"' +
                ", \"" + account + '\"' +
                ", \"" + password + "\"}";
    }
}
