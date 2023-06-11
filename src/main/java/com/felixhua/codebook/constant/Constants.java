package com.felixhua.codebook.constant;


import com.felixhua.codebook.util.ResourceUtil;
import javafx.stage.FileChooser;

public class Constants {
    public static final String PROJECT_URL = "https://github.com/Fermansky/CodeBook";
    public static final String DEFAULT_ICON = ResourceUtil.getImage("icon_200px.png");

    public static final FileChooser.ExtensionFilter CODE_BOOK_EXTENSION_FILTER = new FileChooser.ExtensionFilter("密码簿", "*.cb");
}
