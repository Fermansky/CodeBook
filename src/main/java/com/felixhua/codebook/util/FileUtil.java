package com.felixhua.codebook.util;

import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;

import java.io.*;

public class FileUtil {
    static File file = new File("codebook.cb");

    public static void loadFile() throws IOException {
        checkFileExistence();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            CodeUtil.readKeyLine(line);
            while ((line = reader.readLine()) != null) {
                String dataLine = CodeUtil.decryptAES(line);
                DataUtil.parseDataLine(dataLine, MainController.getContentDataList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile() throws IOException {
        checkFileExistence();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String password = LoginController.getInstance().getPassword();
            CodeUtil.generateNewKeyAndIV();
            String encryptedPassword = CodeUtil.encryptAES(password, CodeUtil.getKey(), CodeUtil.getIV());
            writer.write(CodeUtil.encode(CodeUtil.getKey()) + encryptedPassword + CodeUtil.encode(CodeUtil.getIV()));
            writer.newLine();
            String contentData = MainController.getContentDataList().toString().replaceAll(" ", "");
            writer.write(CodeUtil.encryptAES(contentData));
        } catch (Exception e) {
            e.printStackTrace();
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
