package com.example.hospital.utils;

import org.springframework.util.DigestUtils;

public class PasswordUtils {
    private static final String slat = "123@abc";
    private PasswordUtils() {}
    public static String getRealPassword(String password){
        password = DigestUtils.md5DigestAsHex((password + slat).getBytes());
        return password;
    }
}
