package com.exam.util.type;

import com.exam.config.BaseStatic;

public class ParseUtil {

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(Object value) {
        if (value instanceof String)
        {return parseInt(((String) value));}
        else if (value instanceof Integer)
        { return (Integer) value;}
        return null;
    }

    public static Boolean parseBoolean(Object value) {
        if (value instanceof String)
        {return parseBoolean(((String) value));}
        else if (value instanceof Boolean)
        {return (Boolean) value;}
        return null;
    }

    public static Boolean parseBoolean(String value) {
        if (BaseStatic.BOOLEN_TRUE.equals(value))
        {return Boolean.TRUE;}
        else if (BaseStatic.BOOLEN_FALSE.equals(value))
        {return Boolean.FALSE;}
        return null;
    }
}
