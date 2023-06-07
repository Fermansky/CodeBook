package com.felixhua.codebook.util;

import java.io.File;
import java.util.Objects;

public class ResourceUtil {
    public static String getImage(String fileName) {
        return Objects.requireNonNull(ResourceUtil.class.getResource("/img/" + fileName)).toExternalForm();
    }
}
