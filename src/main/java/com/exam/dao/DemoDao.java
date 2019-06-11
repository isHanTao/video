package com.exam.dao;

import org.apache.ibatis.annotations.Param;

/**
 * dao层，类上不需要加注解
 */
public interface DemoDao {
    /**
     * 参数需要使用@Param注解，该值用于xml文件
     *  必须抛出异常
     */
    String count(@Param("key") String key) throws RuntimeException;
}
