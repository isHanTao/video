package com.exam.util.encrypt;


import com.exam.config.BaseStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @description: AES加密工具类
 * @author: maojialong
 * @date: 2017年11月7日 上午10:11:02
 */
public class AESEncryptUtil implements DecryptAble {

    //实例化密钥
    private Key keyInstance;

    //原始密钥
//    private String key = "my-springmvc-2017-11-07";

    //密钥算法
    private static String KEY_ALGORITHM = "AES";

    //加密-解密算法 / 工作模式 / 填充方式
    private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
private Logger logger = LoggerFactory.getLogger(getClass());
    public AESEncryptUtil(String key) {
        try {
            logger.info("秘钥="+key+"-");
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kgen.init(128, random);
            keyInstance = kgen.generateKey();
            kgen = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;}
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * @param str
     * @return
     * @description: AES对称加密字符串，并通过Jdk自带Base64转换为ASCII
     * @author: Administrator
     * @date: 2017年11月7日 上午9:37:48
     */
    @Override
    public String encrypt(String str) {
        try {
            byte[] bytes = str.getBytes(BaseStatic.CHARSET);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyInstance);
            byte[] doFinal = cipher.doFinal(bytes);
            return parseByte2HexStr(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param
     * @return
     * @description: 对AES加密字符串进行解密
     * @author: maojialong
     * @date: 2017年11月7日 上午10:14:00
     */
    @Override
    public String decrypt(String value) {
        try {
            byte[] bytes = parseHexStr2Byte(value.trim());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyInstance);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, BaseStatic.CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}