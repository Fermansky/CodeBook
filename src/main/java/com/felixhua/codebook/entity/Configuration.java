package com.felixhua.codebook.entity;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private static Configuration instance = new Configuration();
    private List<CodeBook> codeBookList = new ArrayList<>();

    public static Configuration getInstance() {
        return instance;
    }

    public static void addCodeBook(CodeBook codeBook) {
        instance.codeBookList.add(codeBook);
    }

    public static List<CodeBook> getCodeBookList() {
        return instance.codeBookList;
    }

    public static void setInstance(Configuration configuration) {
        instance = configuration;
    }

    private Configuration() {

    }
}
