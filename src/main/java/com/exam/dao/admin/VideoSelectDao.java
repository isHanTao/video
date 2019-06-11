package com.exam.dao.admin;

import com.exam.bean.admin.VideoBean;
import com.exam.util.type.StringUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 王曦
 * @Project: video
 * @Package:com.exam.dao.admin
 * @date 2018/10/12 8:59
 * @description 通过输入字段模糊查询名称（Dao层）
 **/
public interface VideoSelectDao {
    /**
     * 通过输入字段模糊查询名称
     * @param name String
     * @return List<VideoBean>
     * @throws RuntimeException
     */
    List<VideoBean> selectVideoByName(@Param("name") String name) throws RuntimeException;

    /**
     * 条件查询
     * @param classify String
     * @param labelId String
     * @param grade String
     * @throws RuntimeException
     * @return  List<VideoBean>
     */
    List<VideoBean> selectVideoByInfo(@Param("classify") String classify, @Param("labelId") String labelId, @Param("grade") String grade) throws RuntimeException;
}
