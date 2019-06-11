package com.exam.service.admin;

import com.exam.bean.admin.VideoBean;

/**
 * @author WDC
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/11 16:46
 * @description 这是一个对应的视频上传的Service接口
 **/

public interface VideoUploadService {
    void videoUpload(VideoBean videoBean) throws RuntimeException;

    /**
     * 根据视频Id修改对应的status为0
     * @param id 视频id
     * @return 数据库被影响的行数
     * @author moujie
     * @throws RuntimeException 往上抛出
     */
    Integer uploadOver( Integer id) throws RuntimeException;
}
