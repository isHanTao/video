package com.exam.util.code;

import com.exam.config.BaseStatic;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.UUID;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.util.code
 * @date 2018/9/20 14:36
 * @description
 **/
public class CodeUtil {
    public static String createToken() {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken() {
        String token =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(BaseStatic.KEY_SESSION_TOKEN);
        return token;
    }
}
