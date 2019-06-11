package com.exam.service.admin.impl;


import com.exam.bean.admin.VideoBean;
import com.exam.dao.admin.VideoModifyDao;
import com.exam.service.admin.AddLogInfoService;
import com.exam.service.admin.LabelService;
import com.exam.service.admin.VideoModifyService;
import com.exam.util.type.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;

/**
 * @Project: video
 * @Package: com.exam.service.admin.impl
 * @Author: 韩涛
 * @Date: 2018-10-12 11:28
 * @Description: 更改或删除视频信息
 * @Param:
 **/
@Service
public class VideoModifyServiceImpl implements VideoModifyService{

    @Autowired
    VideoModifyDao videoModify;
    @Autowired
    AddLogInfoService addLogInfoService;
    @Autowired
    VideoModifyService videoModifyService;
    @Autowired
    LabelService labelService;
    Integer[] grades = {0, 1, 2, 3};
    Integer[] classifies = {0, 1, 2};
    @Override
    //@CacheEvict(value = "videos",allEntries = true)
    public void deleteVideoById(Integer videoId) throws Exception {
        String videoName = videoModifyService.getVideoById(videoId).getVideoName();
        videoModify.deleteVideoById(videoId);
        addLogInfoService.insertLogInfo("删除","V"+videoId,videoName,1);
    }

    @Override
    public List<VideoBean> selectVideos() {
        return videoModify.selectVideos();
    }

    @Override
    //@CacheEvict(value = "videos",allEntries = true)
    public void modifyVideoById(VideoBean videoBean) throws Exception {
        String videoName = videoModifyService.getVideoById(videoBean.getVideoId()).getVideoName();
        videoModify.modifyVideoById(videoBean);
        addLogInfoService.insertLogInfo("修改","V"+videoBean.getVideoId(),videoName,1);
    }

    @Override
    public VideoBean getVideoById(Integer videoId) {
        return videoModify.getVideoById(videoId);
    }

    @Override
    public boolean checkParam(Map<String, String> videoInfo) throws Exception {

        Integer videoId = ParseUtil.parseInt(videoInfo.get("videoId"));
        Integer grade = ParseUtil.parseInt(videoInfo.get("grade"));
        Integer classify = ParseUtil.parseInt(videoInfo.get("classify"));
        String label = videoInfo.get("label");

        if (videoId == null || StringUtils.isEmpty(videoModify.getVideoById(videoId))){
            return false;
        }
        if (grade == null || checkHaveItem(grade,grades)){
            return false;
        }
        if (classify == null || checkHaveItem(classify,classifies) || StringUtils.isEmpty(labelService.queryLabelId(label,classify))){
            return false;
        }

        return true;
    }

    @Override
    public VideoBean getNewVideoBean(Map<String, String> videoInfo) throws Exception {

        Integer videoId = ParseUtil.parseInt(videoInfo.get("videoId"));

        Integer grade = ParseUtil.parseInt(videoInfo.get("grade"));
        String label = videoInfo.get("label");
        String videoDescribe = videoInfo.get("videoDescribe");
        String videoName = videoInfo.get("videoName");
        Integer classify = ParseUtil.parseInt(videoInfo.get("classify"));


        VideoBean videoBean = videoModify.getVideoById(videoId);

            if (!StringUtils.isEmpty(grade)){
                videoBean.setGrade(grade);
            }
            if(!StringUtils.isEmpty(videoDescribe)){
                videoBean.setVideoDescribe(videoDescribe);
            }
            if (!StringUtils.isEmpty(videoName)){
                videoBean.setVideoName(videoName);
            }
            if (!StringUtils.isEmpty(label)){
                Integer labelId =labelService.queryLabelId(label,classify);
                videoBean.setLabelId(labelId);
            }
        return videoBean;
    }

    private boolean checkHaveItem(Integer item, Integer[] values){
        boolean flag = true;
        for (Integer value : values) {
            if (value.equals(item) ){
                flag = false;
                return flag;
            }
        }
        return flag;
    }
}
