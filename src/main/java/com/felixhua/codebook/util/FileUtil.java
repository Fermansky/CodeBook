package com.felixhua.codebook.util;

import com.felixhua.codebook.controller.LoginController;

import java.io.*;

public class FileUtil {
    static File file = new File("codebook.cb");

    public static void loadFile() throws IOException {
        checkFileExistence();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            LoginController.getInstance().setPassword(CodeUtil.getPassword(line));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void writeFile() throws IOException {
        checkFileExistence();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String password = LoginController.getInstance().getPassword();
            int timeStamp = CodeUtil.getTimestamp();
            char sign = (char) ('H' + timeStamp);
            String encodedPassword = CodeUtil.encrypt(password, timeStamp+1);
            writer.write(sign + encodedPassword);
        }
    }

    private static void checkFileExistence() throws IOException {
        if(!file.exists()) {
            System.out.println("文件不存在，将为您创建新文件");
            if (!file.createNewFile()) {
                System.err.println("创建文件失败");
            }
        }
    }
}
