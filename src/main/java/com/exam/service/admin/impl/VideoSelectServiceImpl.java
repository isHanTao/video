package com.exam.service.admin.impl;

import com.exam.bean.admin.VideoBean;
import com.exam.dao.admin.VideoSelectDao;
import com.exam.service.admin.LabelService;
import com.exam.service.admin.VideoSelectService;
import com.exam.util.type.ParseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 王曦
 * @Project: video
 * @Package:com.exam.service.admin.Impl
 * @date 2018/10/12 10:28
 * @description 通过输入字段模糊查询名称（Server实现类）
 **/
@Service
public class VideoSelectServiceImpl implements VideoSelectService {
    @Autowired
    VideoSelectDao videoSelectDao;
    @Autowired
    LabelService labelService;

    @Override
    public List<VideoBean> selectVideoByName(String name) throws RuntimeException {
        name = "%" + name + "%";
        return videoSelectDao.selectVideoByName(name);
    }

    @Override
    public PageInfo selectVideoByInfo(String classify, String label, String grade, Integer pageNum) throws Exception {
        Integer sLabelId = -1;
        if(classify.length()!=0 &&label.length()!=0){
            sLabelId = labelService.queryLabelId(label, Integer.parseInt(classify));
        }
        String labelId;
        if (StringUtils.isEmpty(sLabelId) || sLabelId == -1 ) {
            labelId = "%";
        }else {
            labelId = "%" + sLabelId;
        }
        classify = "%" + classify;
        grade = "%" + grade;
        PageHelper.startPage(pageNum, 5);
        List<VideoBean> videos = videoSelectDao.selectVideoByInfo(classify,
                 labelId, grade);
        PageInfo pageInfo = new PageInfo(videos,10);
        return pageInfo;
    }
}
