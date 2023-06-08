package com.felixhua.codebook.util;

import com.felixhua.codebook.controller.LoginController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class CodeUtil {
    private static byte[] key;
    private static byte[] iv;

    public static String encryptAES(String data, byte[] key, byte[] iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;

            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv);  // CBC模式，需要一个向量iv，可增加加密算法的强度

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return encode(encrypted).trim(); // BASE64做转码。

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptAES(String data) throws Exception {
        return encryptAES(data, key, iv);
    }

    public static String decryptAES(String data, byte[] key, byte[] iv) throws Exception {
        try
        {
            byte[] encrypted1 = decode(data);//先用base64解密

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptAES(String data) {
        try {
            return decryptAES(data, key, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(byte[] byteArray) {
        return new String(Base64.getEncoder().encode(byteArray));
    }

    public static byte[] decode(String base64EncodedString) {
        return Base64.getDecoder().decode(base64EncodedString.getBytes());
    }

    public static byte[] getKey() {
        return key;
    }

    public static byte[] getIV() {
        return iv;
    }

    private static byte[] generateRandomBytes() {
        byte[] bytes = new byte[16]; // 16字节的初始化向量
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    public static void generateNewKeyAndIV() {
        key = generateRandomBytes();
        iv = generateRandomBytes();
    }

    public static void readKeyLine(String keyLine) throws Exception {
        String keyString = keyLine.substring(0, 24);
        String passwordString = keyLine.substring(24, keyLine.length()-24);
        String ivString = keyLine.substring(keyLine.length() - 24);
        key = decode(keyString);
        iv = decode(ivString);
        LoginController.getInstance().setPassword(decryptAES(passwordString, key, iv));
    }
}
