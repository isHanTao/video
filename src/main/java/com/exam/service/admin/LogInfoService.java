package com.exam.service.admin;

import com.exam.bean.admin.LogBean;

import java.util.List;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/12 10:35
 * @description
 **/
public interface LogInfoService {

    /**
     * 获取日志
     * @param timeStart
     * @param timeEnd
     * @return 日志列表
     * @throws Exception
     */
    public List<LogBean> findLogInfo(String timeStart,String timeEnd )throws Exception;
}
