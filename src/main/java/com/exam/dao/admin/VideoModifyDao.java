package com.exam.dao.admin;

import com.exam.bean.admin.VideoBean;
import org.junit.Test;

import java.util.List;

/**
 * @Project: video
 * @Package: com.exam.dao.admin
 * @Author: 韩涛
 * @Date: 2018-10-12 9:08
 * @Description:
 * @Param:
 **/
public interface VideoModifyDao {
    /**
     * 通过id删除视频信息
     * @param videoId
     */
    void deleteVideoById(Integer videoId);

    /**
     * 获取所有视频信息
     * @return 视频列表
     */
    List<VideoBean> selectVideos();

    /**
     * 更改视频信息
     * @param videoBean
     */
    void modifyVideoById(VideoBean videoBean);

    /**
     * 根据id获取视频信息
     * @param videoId
     * @return 视频信息
     */
    VideoBean getVideoById(Integer videoId);
}
