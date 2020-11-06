package com.asteroid.expression.common.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * spring-security登陆的密码进行MD5加密传到数据库
 * @author: YuSai
 * @date: 2020-10-21 17:19
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    public static final int time = 5;

    public static final String SALT = "springsecurity";


    @Override
    public String encode(CharSequence rawPassword) {
        try {
            String password = "";
            MessageDigest digest = MessageDigest.getInstance("MD5");
            for (int i = 0; i < time; i++) {
                byte[] bytes = digest.digest((password + SALT).getBytes("UTF-8"));
                password = String.format("%032x", new BigInteger(1, bytes));
            }
            return password;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(new CustomPasswordEncoder().encode("123456"));;
    }

}
