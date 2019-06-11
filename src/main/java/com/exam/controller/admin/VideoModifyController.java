package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.bean.admin.VideoBean;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.VideoModifyService;
import com.exam.util.type.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author: 韩涛
 * @Date: 2018-10-11 14:28
 * @Description:
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class VideoModifyController {

    @Autowired
    VideoModifyService videoModify;

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/deleteVideo",method = RequestMethod.DELETE)
    public Msg deleteVideo(@RequestBody Map<String, String> videoId) throws Exception {
        Integer id = ParseUtil.parseInt(videoId.get("videoId"));
        if (StringUtils.isEmpty(id)) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "参数错误");
        } else {
            if (StringUtils.isEmpty(videoModify.getVideoById(id))) {
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "视频不存在");
            }
            videoModify.deleteVideoById(id);
        }
        return Msg.success("删除成功");
    }

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/modifyVideo", method = RequestMethod.PUT)
    public Msg modifyVideo(@RequestBody Map<String, String> videoInfo) throws Exception {
        boolean flag = videoModify.checkParam(videoInfo);
        if (flag) {
            VideoBean videoBean = videoModify.getNewVideoBean(videoInfo);
            videoModify.modifyVideoById(videoBean);
            return Msg.success("更改成功");
        } else {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "参数错误");
        }
    }

}
