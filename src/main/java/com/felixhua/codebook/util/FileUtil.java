package com.felixhua.codebook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static void loadFile() throws IOException {
        File file = new File("/codebook.cb");
        if(!file.exists()) {
            System.out.println("文件不存在，将为您创建新文件");
            file.createNewFile();
        }
        FileInputStream fileInputStream = new FileInputStream(file);

    }
}
