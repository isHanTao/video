package com.exam.annotation;

import com.exam.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.annotation
 * @date 2018/9/19 15:52
 * @description
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    //权限身份
    RoleEnum[] roles() default {};
//    //是否生成token
//    boolean createToken () default true;
//    //是否使用token
//    boolean checkToken() default false;
}
