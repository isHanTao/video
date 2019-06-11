package com.exam.dao.admin;

import com.exam.bean.admin.LogBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.dao.admin
 * @date 2018/10/12 8:35
 * @description
 **/
public interface LogInfoDao {
    /**
     * 获取日志列表
     * @param timeStart
     * @param timeEnd
     * @return 日志列表
     * @throws Exception
     */
    List<LogBean> logInfoObtain(@Param("timeStart") String timeStart,@Param("timeEnd") String timeEnd)throws Exception;
}
