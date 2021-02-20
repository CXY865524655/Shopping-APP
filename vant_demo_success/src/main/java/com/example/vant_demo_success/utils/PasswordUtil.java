package com.example.vant_demo_success.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Package_Name: com.example.vant_demo_success.utils
 * @ClassName: passwordUtil
 * @Description:
 * @Author: cxy
 * @Date: 2020/3/31 23:26
 */
public class PasswordUtil {
    public static String encoderByMd5(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(md5.digest(pwd.getBytes("utf-8")));
        return encode;
    }

//    public static boolean checkLogin(String input,String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        if (encoderByMd5(input).equals(pwd)){
//            return true;
//        }else {
//            return false;
//        }
//    }


    public static boolean checkLogin(String input,String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (input.equals(pwd)){
            return true;
        }else {
            return false;
        }
    }
}
