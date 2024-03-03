package com.felixhua.codebook.util;

import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.CodeBook;
import com.felixhua.codebook.entity.Configuration;
import com.felixhua.codebook.entity.ContentData;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static File file = new File("codebook.cb");
    public static File configFile = new File("config.json");
    public static Gson gson = new Gson();

    public static String loadConfigFile() {
        if (configFile == null || !configFile.exists()) {
            System.err.println("配置文件不存在");
            return "";
        }
        try (FileReader reader = new FileReader(configFile)) {
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            if (configuration != null) {
                Configuration.setInstance(configuration);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String writeConfigFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write(gson.toJson(Configuration.getInstance()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String loadCodeBook(CodeBook codeBook) {
        if (codeBook == null || codeBook.getPath() == null) {
            System.err.println("密码簿无效");
            return "密码簿无效";
        }
        File file = new File(codeBook.getPath());
        if (!file.exists()) {
            System.err.println("密码簿文件不存在");
            return "密码簿文件不存在";
        }
        if (codeBook.getPassword() == null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                codeBook.setPassword(CodeUtil.readKeyLine(line));
                while ((line = reader.readLine()) != null) {
                    String dataLine = CodeUtil.decryptAES(line);
                    DataUtil.parseDataLine(dataLine, codeBook.getContentDataList());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeCodeBook(File codebook){
        if (codebook == null) {
            return;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(codebook))) {
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

    public static void writeCodeBook(CodeBook codeBook) {
        if (codeBook == null) {
            return;
        }
        File file = new File(codeBook.getPath());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String password = codeBook.getPassword();
            CodeUtil.generateNewKeyAndIV();
            String encryptedPassword = CodeUtil.encryptAES(password, CodeUtil.getKey(), CodeUtil.getIV());
            writer.write(CodeUtil.encode(CodeUtil.getKey()) + encryptedPassword + CodeUtil.encode(CodeUtil.getIV()));
            writer.newLine();
            String contentData = codeBook.getContentDataList().toString().replaceAll(" ", "");
            writer.write(CodeUtil.encryptAES(contentData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeCodeBook() {
        writeCodeBook(file);
    }

    public static void exportCSV(File file) {
        try(FileWriter fileWriter = new FileWriter(file);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            for(ContentData contentData : MainController.getContentDataList()) {
                csvPrinter.printRecord(contentData.toStringList());
            }
            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importCSV(File file) {
        try(FileReader fileReader = new FileReader(file);
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {
            List<CSVRecord> records = csvParser.getRecords();
            List<ContentData> contentDataList = new ArrayList<>();
            for (CSVRecord record : records) {
                contentDataList.add(new ContentData(record.get(0), record.get(1), record.get(2)));
            }
            for(ContentData contentData : contentDataList) {
                MainController.addContentData(contentData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkFileExistence(){
        if(!file.exists()) {
            System.out.println("文件不存在，将为您创建新文件");
            try {
                if (!file.createNewFile()) {
                    System.err.println("创建文件失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void importCodeBook(File codebook) {
        file = codebook;
//        loadCodeBook();
    }
}
