package com.exam.service.admin;

import com.exam.bean.Msg;
import org.apache.ibatis.annotations.Param;


/**
 * @author 罗致远
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/11 17:20
 * @description
 **/
public interface LabelService {
    /**
     * 根据分类查询所有标签
     * @param classify 分类
     * @param pageNum 分页
     * @return Msg类
     * @throws Exception
     */
    Msg queryLabel(@Param("classify") String classify, @Param("pageNum") String pageNum) throws Exception;

    /**
     * 根据标签id查询标签详情
     * @param labelId 标签id
     * @return Msg类
     * @throws Exception
     */
    Msg queryLabelDetail(@Param("labelId") String labelId) throws Exception;

    /**
     * 根据分类和标签名查询标签id
     * @param label 标签名
     * @param classify 分类
     * @return 标签id
     * @throws Exception
     */
    Integer queryLabelId(@Param("label") String label, @Param("classify") Integer classify) throws Exception;

    /**
     * 插入新标签
     * @param label 标签名
     * @param classify 标签分类信息
     * @return
     * @throws Exception
     */
    Msg addLabel(@Param("label") String label, @Param("classify") String classify) throws Exception;

    /**
     * 修改标签
     * @param labelId 标签id
     * @param newClassify 修改的新分类
     * @param newLabel 修改的新标签名
     * @return
     * @throws Exception
     */
    Msg modifyLabel(@Param("labelId") String labelId, @Param("newClassify") String newClassify, @Param("newLabel") String newLabel) throws Exception;

    /**
     * 删除标签
     * @param labelId 标签id
     * @return
     * @throws Exception
     */
    Msg deleteLabel(@Param("labelId") String labelId) throws Exception;
}
