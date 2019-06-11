package com.exam.service.admin;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/12 14:26
 * @description
 **/
public interface AddLogInfoService {

    /**
     * 插入日志数据
     * @param operation
     * @param code
     * @param name
     * @param status
     * @throws Exception
     */
    public void  insertLogInfo(String operation,String code,String name ,Integer status ) throws  Exception;
}
