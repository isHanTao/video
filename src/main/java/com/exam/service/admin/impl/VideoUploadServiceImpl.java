package com.exam.service.admin.impl;

import com.exam.bean.admin.VideoBean;
import com.exam.dao.admin.VideoUploadDao;
import com.exam.service.admin.VideoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WDC
 * @Project: video
 * @Package:com.exam.service.admin.impl
 * @date 2018/10/11 17:09
 * @description Service层接口实现类
 **/
@Service
public class VideoUploadServiceImpl implements VideoUploadService {
    @Autowired
    VideoUploadDao videoUploadDao;
    /**
     * @description  视频上传 Service层接口实现类的实现方法
     * @param videoBean
     * @return void
     * @author WDC
     * @date 2018/10/12
     */
    @Override
    public void videoUpload(VideoBean videoBean) throws RuntimeException {
        videoUploadDao.savaVideoInformation(videoBean);
    }

    @Override
//    @CacheEvict(value = "videos",allEntries = true)
    public Integer uploadOver(Integer id) throws RuntimeException {
        return videoUploadDao.uploadOver(id);
    }
}
