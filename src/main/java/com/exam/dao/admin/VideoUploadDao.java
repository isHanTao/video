package com.exam.dao.admin;

import com.exam.bean.admin.VideoBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author WDC
 * @Project: video
 * @Package:com.exam.dao
 * @date 2018/10/11 16:48
 * @description
 **/
public interface VideoUploadDao {
    /**
     * @description 这是一个mapper.xml文件对应的Dao层接口
     * @param videoBean
     * @return void
     * @author WDC
     * @date 2018/10/12
     */
    void savaVideoInformation(VideoBean videoBean) throws RuntimeException;

    /**
     *
     * 根据Id修改对应的状态
     * @param  videoId  视频Id
     * @return 数据库修改影响的行数
     * @throws RuntimeException 抛出异常
     * @author moujie
     */
    Integer uploadOver(@Param("videoId") Integer videoId)throws RuntimeException;
}
