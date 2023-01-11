package com.java.challenge.hcase02.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {

    public static Integer fetchRandomIntegerBetween1And100() {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

    public static String generateMD5HashValue(Timestamp createdDate, Integer randomInt) {
        try {

            String input = createdDate + String.valueOf(randomInt);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            return hashText.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5Last2Char(String md5HashValue) {
        char[] chars = md5HashValue.toCharArray();
        int length = chars.length;
        char aChar = chars[length - 1];
        char aChar2 = chars[length - 2];

        return String.valueOf(aChar2) + aChar;
    }
}