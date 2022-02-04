package com.mercadolibre.integrativeproject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {


    public static String encoder(String password) {
        if (password != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encode = bCryptPasswordEncoder.encode(password);
            return encode;
        }

        return null;
    }

    public static boolean isValidPassword(String password, String encoderPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(encoder(password));
        return bCryptPasswordEncoder.matches(password, encoderPassword);
    }
}
