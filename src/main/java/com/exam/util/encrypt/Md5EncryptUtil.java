package com.exam.util.encrypt;


import com.exam.config.BaseStatic;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/15
 * @Time 20:41
 * @Descorption 加密类
 */
public class Md5EncryptUtil {
    private static final byte key = 7;


    public static String parseMd5(String value) {
        return parseMd5(value, "");
    }

    /**
     * 功能的描述: 将明文信息进行128位MD5加密
     *
     *
     * @param salt     盐
     * @return 返回32位小写字母的md5码
     */
    public static String parseMd5(String value, String salt) {
        //确定计算方法
        try {
            value += salt;
            return DigestUtils.md5DigestAsHex(value.getBytes(BaseStatic.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String encrypt(String value) {
        //确定计算方法
        try {
            value += BaseStatic.MD5_SALT;
            return DigestUtils.md5DigestAsHex(value.getBytes(BaseStatic.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能的描述: 将明文(密码)信息加盐后进行128位MD5加密,返回两个值：加密结果和盐
     *
     * @param password 需要加密的明文信息
     * @return 第一位返回32位小写字母的md5码, 第二位 盐
     */
    public static String[] parseMd5WithSalt(String password) {
        String[] result = new String[2];
        String salt = getRandomString(8);
        result[0] = parseMd5(password, salt);
        result[1] = salt;
        return result;
    }

    /**
     * 功能的描述: 将明文密码+盐进行Md5运算后，与在数据库中保存的md5值进行比较
     *
     * @param pass    明文密码
     * @param salt    盐
     * @param md5Pass 加密后的md5密码
     * @return true，表示比较结果相同，说明验证通过，false 验证不同过 Create Date:2016-4-13
     */
    public static boolean comparePass(String pass, String salt, String md5Pass) {
        String md5 = parseMd5(pass, salt);
        return md5 != null && md5.equals(md5Pass);
    }

    public static boolean comparePass(String pass, String md5Pass) {
        String md5 = parseMd5(pass, BaseStatic.MD5_SALT);
        return md5 != null && md5.equals(md5Pass);
    }

    /**
     * 功能的描述: 生成指定长度的随机字符串
     *
     * @param length
     * @return Create Date:2016-4-13
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}