package com.felixhua.codebook.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class DesktopUtil {
    public static void browse(String url) {
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
