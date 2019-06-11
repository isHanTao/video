package com.exam.service.admin.impl;

import com.exam.bean.admin.LogBean;
import com.exam.dao.admin.LogInfoDao;
import com.exam.service.admin.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.service.admin.impl
 * @date 2018/10/12 10:41
 * @description
 **/
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    LogInfoDao logInfoDao;
    @Override
    public List<LogBean> findLogInfo(String timeStart, String timeEnd) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long startLong=Long.valueOf(timeStart);
        String timeBegin=sdf.format(startLong);
        Long endLong=Long.valueOf(timeEnd);
        String timeOver=sdf.format(endLong);
        return logInfoDao.logInfoObtain(timeBegin,timeOver);

    }
}
