package com.felixhua.codebook.util;

import com.felixhua.codebook.controller.LoginController;
import com.felixhua.codebook.controller.MainController;
import com.felixhua.codebook.entity.ContentData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    static File file = new File("codebook.cb");

    public static void loadCodeBook() throws IOException {
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

    public static void writeCodeBook() throws IOException {
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
            System.out.println(contentDataList);
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
