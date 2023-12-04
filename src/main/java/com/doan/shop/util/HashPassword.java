package com.doan.shop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashMD5(String data) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Mã hóa dữ liệu và chuyển kết quả sang dạng hex
            byte[] hashedBytes = md.digest(data.getBytes());
            StringBuilder hexStringBuilder = new StringBuilder();
            
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
