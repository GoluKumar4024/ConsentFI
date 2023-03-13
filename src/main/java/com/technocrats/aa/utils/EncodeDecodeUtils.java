package com.technocrats.aa.utils;


import org.apache.commons.codec.binary.Base64;

import java.util.Random;

public class EncodeDecodeUtils {

    public static String base64Encode(String data) {
        return new String(new Base64().encode(data.getBytes()));
    }

    public static String base64Decode(String data) {
        return new String(new Base64().decode(data.getBytes()));
    }

    public static String getRandomNonce(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length - 1; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        sb.append("=");
        return sb.toString();
    }

}
