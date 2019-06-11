package com.exam.service.admin;

import com.exam.bean.admin.VideoBean;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王曦
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/12 10:27
 * @description 通过输入字段模糊查询名称（Server接口）
 **/
public interface VideoSelectService {
    /**
     * 通过输入字段模糊查询名称
     * @param name String
     * @throws RuntimeException
     * @return List<VideoBean>
     */
    List<VideoBean> selectVideoByName(String name) throws RuntimeException;

    /**
     * 通过筛选条件选择视频
     * @param classify String
     * @param labelId String
     * @param grade String
     * @param pageNum String
     * @return PageInfo
     * @throws Exception
     */
    PageInfo selectVideoByInfo(@Param("classify") String classify, @Param("labelId") String labelId, @Param("grade") String grade, @Param("pageNum") Integer pageNum) throws Exception;

}
