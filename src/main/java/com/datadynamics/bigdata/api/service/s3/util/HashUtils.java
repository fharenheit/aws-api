package com.datadynamics.bigdata.api.service.s3.util;

import com.amazonaws.util.BinaryUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static byte[] hash(String prefix, byte[] bytes) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
            localMessageDigest.update(bytes);
            byte[] paramByte = localMessageDigest.digest();
            String base64Binary = DatatypeConverter.printBase64Binary(paramByte);

            System.out.println(String.format("[Orig] %s", new String(bytes)));
            System.out.println(String.format("[Hexa] %s: %s", prefix, BinaryUtils.toHex(paramByte)));
            System.out.println(String.format("[Hash] %s: %s", prefix, base64Binary));

            return base64Binary.getBytes();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static byte[] hash(String prefix, String paramString) {
        try {
            System.out.println(String.format("-------------- [%s] START --------------", prefix));
            return hash(prefix, paramString.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            System.out.println(String.format("-------------- [%s] END --------------\n", prefix));
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        hash("Test", "HELLO\nWORLD");
        System.out.println(com.amazonaws.util.BinaryUtils.toHex("HELLO\nWORLD".getBytes()));
    }
}
