package com.felixhua.codebook.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CodeUtil {
    public static String encrypt(String plaintext, int time) {
        String code = plaintext;
        for(int i = 0; i < time; i++) {
            code = encode(code);
        }
        return code;
    }

    public static String decrypt(String code, int time) {
        String plaintext = code;
        for(int i = 0; i < time; i++) {
            plaintext = decode(plaintext);
        }
        return plaintext;
    }

    public static int getTimestamp() {
        long l = System.currentTimeMillis();
        return (int) (l % 5);
    }

    public static String getPassword(String code) {
        char sign = code.charAt(0);
        int timestamp = sign - 'H';
        if (timestamp < 0 || timestamp > 4) {
            System.err.println("读取密码失败");
        }
        String encodedPassword = code.substring(1);
        return decrypt(encodedPassword, timestamp+1);
    }

    private static String encode(String plaintext) {
        byte[] encode = Base64.getEncoder().encode(plaintext.getBytes());
        return new String(encode, StandardCharsets.UTF_8);
    }

    private static String decode(String code) {
        byte[] decode = Base64.getDecoder().decode(code.getBytes());
        return new String(decode, StandardCharsets.UTF_8);
    }
}
