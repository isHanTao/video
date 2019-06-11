package com.exam.dao.admin;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.dao.admin
 * @date 2018/10/12 15:16
 * @description
 **/
public interface AddLogInfoDao {
    /**
     * 插入日志
     * @param createTime
     * @param logContent
     * @param status
     * @throws Exception
     */
    public void insertLogInfo(@Param("createTime") Date createTime, @Param("logContent") String logContent, @Param("status") Integer status)throws Exception;
}
