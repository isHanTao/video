package common;

import com.exam.config.BaseStatic;
import com.exam.util.encrypt.AESEncryptUtil;
import com.exam.util.encrypt.Md5EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:java
 * @date 2018/9/20 16:30
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-encrypt.xml")
public class EncrypTest {
    @Autowired
    private AESEncryptUtil aesEncryptUtil;
    @Test
    public void md5(){
        String result = Md5EncryptUtil.parseMd5("123456", BaseStatic.MD5_SALT);
        System.out.println(Md5EncryptUtil.comparePass("123456", BaseStatic.MD5_SALT, result));
    }
    @Test
    public void testEncrypt(){
        String result = aesEncryptUtil.encrypt("123456");
        System.out.println("加密结果："+result);
        result = aesEncryptUtil.decrypt(result);
        System.out.println("解密结果："+result);
    }
}
