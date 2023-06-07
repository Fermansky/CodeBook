package com.felixhua.codebook.util;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResourceUtil {
    public static String getImage(String fileName) {
        URL resource = ResourceUtil.class.getResource("/img/" + fileName);
        if(resource == null) {
            return null;
        }
        return resource.toExternalForm();
    }

    public static String getLocalizedImage(String fileName) {
        int last = fileName.lastIndexOf(".");
        String localizedFileName = fileName.substring(0, last) + "_" + Locale.getDefault() + fileName.substring(last);
        String result = getImage(localizedFileName);
        if(result != null) {
            return result;
        }
        result = getMessage(fileName);
        return result;
    }

    public static String getMessage(String key) {
        return ResourceBundle.getBundle("messages").getString(key);
    }
}
