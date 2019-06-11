package com.exam.dao.admin;

import com.exam.bean.admin.LabelBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 罗致远
 * @Project: video
 * @Package:com.exam.dao
 * @date 2018/10/11 15:50
 * @description 对标签的增删查改
 **/

public interface LabelDao {
    /**
     * 根据分类查询所有标签
     * @param classify 分类
     * @return 标签列表
     * @throws Exception
     */
    List<LabelBean> queryLabel(@Param("classify") String classify) throws Exception;

    /**
     * 根据标签id查询标签详情
     * @param labelId 标签id
     * @return 标签实例
     * @throws Exception
     */
    LabelBean queryLabelDetail(@Param("labelId") Integer labelId) throws Exception;

    /**
     * 根据分类和标签名查询标签id
     * @param label 标签名
     * @param classify 分类
     * @return 标签id
     * @throws Exception
     */
    Integer queryLabelId(@Param("label") String label, @Param("classify") Integer classify) throws Exception;

    /**
     * 查询所有标签id
     * @return 标签id集合
     * @throws Exception
     */
    List<Integer> queryLabelIdAll() throws Exception;

    /**
     * 插入新标签
     * @param label 标签名
     * @param classify 标签分类信息
     * @throws Exception
     */
    void addLabel(@Param("label") String label, @Param("classify") Integer classify) throws Exception;

    /**
     * 修改标签
     * @param labelId 标签id
     * @param newClassify 修改的新标签分类
     * @param newLabel 修改的新标签名
     * @throws Exception
     */
    void modifyLabel(@Param("labelId") Integer labelId, @Param("newClassify") Integer newClassify, @Param("newLabel") String newLabel) throws Exception;

    /**
     * 删除标签
     * @param labelId 标签id
     * @throws Exception
     */
    void deleteLabel(@Param("labelId") Integer labelId) throws Exception;

    /**
     * 将删除的标签对应的视频的标签改为其他
     * @param modifyLabelId 修改的标签id
     * @param oldLabelId 待修改标签id
     * @throws Exception
     */
    void modifyVideos(@Param("modifyLabelId") Integer modifyLabelId, @Param("oldLabelId") Integer oldLabelId)throws Exception;
}
