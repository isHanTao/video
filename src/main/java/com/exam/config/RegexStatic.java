package com.exam.config;

/**
 * 正则表达式
 */
public class RegexStatic {
    /**身份证号*/
    public static final String ID_CARD="(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
    /**中文*/
    public static final String CHINESE="[\\u4e00-\\u9fa5]+";
    /**手机号*/
    public static final String PHONE="^[1][3,4,5,7,8][0-9]{9}$";
    /**数字*/
    public static final String NUMBER="[1-9]+[0-9]*";
    public static final String REGEX = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    /**匹配带后缀的路径*/
    public static final String URL_SUFFIX=".+\\..+$";


    /**
     * XX相关正则
     */

}
