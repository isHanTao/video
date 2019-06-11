package com.exam.config;

/**
 * @author 痞老板
 * @Project: exam
 * @Package:com.exam.config
 * @date 2018/8/31 10:30
 * @description
 **/
public class BaseStatic {

    public static final String CHARSET="utf-8";
    //session权限属性的键
    public static final String KEY_SESSION_ROLE="_KEY_SESSION_ROLE";
    //session账号属性的键
    public static final String KEY_SESSION_ACCOUT="_KEY_SESSION_ACCOUT";
    //session信息map的键
    public static final String KEY_SESSION_INFO="_KEY_SESSION_INFO";
    public static final String KEY_SESSION_NAME="_KEY_SESSION_NAME";
    public static final String KEY_SESSION_TOKEN="X-Token";

    public static final String RESPONSE_CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    public static final String FORM_DATA="multipart/form-data";
    public static final String BOOLEN_TRUE="true";
    public static final String BOOLEN_FALSE="false";
    public static final String COOKIE_NAME="username";
    public static final String COOKIE_ACCOUNT="account";
    public static final String COOKIE_PATH="/";
    public static final String MD5_SALT="PILAOBAN";

}
