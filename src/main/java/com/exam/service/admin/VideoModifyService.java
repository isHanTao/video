package com.exam.service.admin;

import com.exam.bean.admin.VideoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Project: video
 * @Package: com.exam.service.admin
 * @Author: 韩涛
 * @Date: 2018-10-12 11:27
 * @Description:
 * @Param:
 **/


public interface VideoModifyService {

    /**
     * 通过videoId删除视频
     * @param videoId
     * @throws Exception
     */
    void deleteVideoById(@Param("videoId") Integer videoId) throws Exception;

    /**
     * 查询所有视频
     * @return 获取所有视频信息
     * @throws Exception
     */
    List<VideoBean> selectVideos() throws Exception;

    /**
     * 更改视频信息
     * @param videoBean
     * @throws Exception
     */
    void modifyVideoById(@Param("videoBean") VideoBean videoBean) throws Exception;

    /**
     * 通过id查询视频
     * @param videoId
     * @return 返回视频信息
     * @throws Exception
     */
    VideoBean getVideoById(@Param("videoId") Integer videoId) throws Exception;

    /**
     * 检查参数是否合法
     * @param videoInfo
     * @return 是否正确
     * @throws Exception
     */
    boolean checkParam(Map<String, String> videoInfo) throws Exception;

    /**
     * 通过参数更改bean后返回
     * @param videoInfo
     * @return 返回更改后的bean
     * @throws Exception
     */
    VideoBean getNewVideoBean(Map<String, String> videoInfo) throws Exception;
}
