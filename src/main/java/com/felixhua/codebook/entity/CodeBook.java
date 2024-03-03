package com.felixhua.codebook.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodeBook {
    private String name;
    private String path;
    private transient String password;
    private transient boolean speciality = false;
    private transient List<ContentData> contentDataList;

    public void setCbFile(File cbFile) {
        this.name = cbFile.getName();
        this.path = cbFile.getAbsolutePath();
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<ContentData> getContentDataList() {
        if (contentDataList == null) {
            contentDataList = new ArrayList<>();
        }
        return contentDataList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name;
    }

    public CodeBook(File cbFile) {
        setCbFile(cbFile);
    }


}
