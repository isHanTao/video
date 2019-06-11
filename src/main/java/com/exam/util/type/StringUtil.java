package com.exam.util.type;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.util.type
 * @date 2018/9/18 17:08
 * @description
 **/
public class StringUtil {

      public static boolean isNotEmpty(String str) {
            if (str != null && str.length() != 0) {
                return true;
            }
            return false;
        }


        public static boolean isEmpty(String str) {
            if (str != null && str.length() != 0) {
                return false;
            }
            return true;
        }
    }

