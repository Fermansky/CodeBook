package com.felixhua.codebook.util;

import com.felixhua.codebook.entity.ContentData;
import com.felixhua.codebook.exception.DataException;

import java.util.List;

public class DataUtil {
    static int index;
    static String dataLine;

    public static boolean checkFieldValidity(String fieldText) {
        return !fieldText.contains("\"");
    }
    public static void parseDataLine(String line, List<ContentData> contentDataList) throws DataException {
        dataLine = line;
        index = 0;
        if(dataLine.charAt(index) != '[') {
            throw new DataException();
        }
        index ++;
        while(dataLine.charAt(index) != ']') {
            if(dataLine.charAt(index) == '{') {
                contentDataList.add(parseContentData());
                index ++;
                if(dataLine.charAt(index) == ',') {
                    index++;
                }
            } else {
                throw new DataException();
            }
        }
        if (dataLine.length()-1 > index) {
            throw new DataException();
        }
    }

    public static ContentData parseContentData() throws DataException {
        int contentIndex = 0;
        String title = null;
        String account = null;
        String password = null;
        if(dataLine.charAt(index) != '{') {
            throw new DataException();
        }
        index ++;
        while(dataLine.charAt(index) != '}') {
            if(dataLine.charAt(index) != '\"') {
                throw new DataException();
            }
            StringBuilder sb = new StringBuilder();
            index++;
            while(dataLine.charAt(index) != '\"' || dataLine.charAt(index+1) == '\"') {
                if (dataLine.charAt(index) == '\"' && dataLine.charAt(index + 1) == '\"') {
                    sb.append("\"");
                    index += 2;
                    continue;
                }
                sb.append(dataLine.charAt(index));
                index ++;
            }
            index++;
            switch (contentIndex) {
                case 0 -> title = sb.toString();
                case 1 -> account = sb.toString();
                case 2 -> password = sb.toString();
            }
            contentIndex++;
            if(dataLine.charAt(index) != ',') {
                if(dataLine.charAt(index) == '}') {
                    return new ContentData(title, account, password);
                }
                throw new DataException();
            }
            index += 1;
        }
        return new ContentData(title, account, password);
    }
}
