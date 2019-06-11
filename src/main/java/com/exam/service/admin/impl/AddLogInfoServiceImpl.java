package com.exam.service.admin.impl;

import com.exam.dao.admin.AddLogInfoDao;
import com.exam.service.admin.AddLogInfoService;
import com.exam.util.type.StringUtil;
import com.exam.util.type.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李静松
 * @Project: video
 * @Package:com.exam.service.admin.impl
 * @date 2018/10/12 14:30
 * @description
 **/
@Service
public class AddLogInfoServiceImpl implements AddLogInfoService {
    @Autowired
    AddLogInfoDao addLogInfoDao;
    @Override
    public void insertLogInfo(String operation, String code,String name,Integer statusNum) throws Exception {
        String content=null;
        char operationOne='V';
        char operationTwo='L';
        char operationThree='+';
        char operationFour='-';
        if(code.charAt(0)==operationOne){
             content=operation+"编号为："
                    +code.substring(1)+",名称为: "+name+" "+"的视频。";
        }
        if(code.charAt(0)==operationTwo){
             content=operation+"编号为："
                    +code.substring(1)+",名称为: "+name+" "+"的标签。";
        }
        if(code.charAt(0)==operationThree){
             content="管理员登录";
        }
        if(code.charAt(0)==operationFour){
             content="管理员注销";
        }
        if(StringUtil.isNotEmpty(content)){
            Date dateNow =new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr=sdf.format(dateNow);
            addLogInfoDao.insertLogInfo(dateNow,content,statusNum);
        }

    }
}
