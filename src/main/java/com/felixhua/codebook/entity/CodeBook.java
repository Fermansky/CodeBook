package com.felixhua.codebook.entity;

import java.io.File;

public class CodeBook {
    private String name;
    private String path;
    private transient String password;
    private transient boolean speciality = false;

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
