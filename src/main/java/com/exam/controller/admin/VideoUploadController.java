package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.bean.admin.VideoBean;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.AddLogInfoService;
import com.exam.service.admin.LabelService;
import com.exam.service.admin.VideoModifyService;
import com.exam.service.admin.VideoUploadService;
import com.exam.util.type.ParseUtil;
import com.exam.util.type.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author:WDC
 * @Date: 2018-10-11 14:32
 * @Description:
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class VideoUploadController {

    @Autowired
    VideoUploadService videoUploadService;
    @Autowired
    LabelService labelService;
    @Autowired
    VideoModifyService videoModifyService;
    @Autowired
    AddLogInfoService addLogInfoService;
//    @Autowired
//    AddLogInfoService addLogInfoService;

    /**
     * @param videoInfo
     * @return com.exam.bean.Msg
     * @description 保存视频信息，并返回刚刚插入的视频的videoId
     * @author WDC
     * @date 2018/10/12
     */
    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/videoUpload", method = RequestMethod.POST)
    public Msg videoUpload(@RequestBody Map<String, String> videoInfo) throws Exception {
        String videoName = videoInfo.get("videoName");
        String videoDescribe = videoInfo.get("videoDescribe");
        String label = videoInfo.get("label");
        VideoBean videoBean = new VideoBean();
        int judge = check(videoInfo);
        switch (judge) {
            case 10:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "请输入参数");
            case 12:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "视频名称长度不符");
            case 13:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "视频描述过长");
            case 14:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "标签未添加描述或描述过长");
            case 15:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "请选择已有年级");
            case 16:
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "请选择已有分类");
            default:
                break;
        }
        int grade;
        int checkJudgeNumber = 10;
        if (judge < checkJudgeNumber) {
            grade = judge;
            int classify = Integer.parseInt(videoInfo.get("classify"));
            int labelId;
            try {
                labelId = labelService.queryLabelId(label, classify);
            } catch (NullPointerException e) {
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "没有此标签");
            }
            videoBean.setVideoName(videoName);
            videoBean.setVideoDescribe(videoDescribe);
            videoBean.setGrade(grade);
            videoBean.setLabelId(labelId);
            videoBean.setStatus(0);
        }
        videoUploadService.videoUpload(videoBean);
        int videoId = videoBean.getVideoId();
        addLogInfoService.insertLogInfo("创建", "V" + videoId, videoBean.getVideoName(), 1);
        return Msg.success("插入成功", videoId);
    }


    private int check(Map<String, String> videoInfo) {
        if (!(videoInfo.containsKey("videoName") && videoInfo.containsKey("grade") && videoInfo.containsKey("videoDescribe") &&
                        videoInfo.containsKey("classify") && videoInfo.containsKey("label"))) {
            return 10;
        }//判断是否有Key
        //取得josn数据
        String videoName = videoInfo.get("videoName");
        String videoDescribe = videoInfo.get("videoDescribe");
        String label = videoInfo.get("label");
        String grades = videoInfo.get("grade");
        //判断视频名称长度  和是否未空
        if (videoName.length() > 50 || (videoName.trim()).length() < 1 || videoName == null) {
            return 12;
        }
        //判断描述最大长度，且判断是否为空
        if ((videoDescribe.trim()).length() > 255 || videoDescribe == null) {
            return 13;
        }
        //判断标签长度 和是否为空
        if ((label.trim()).length() > 20 || (label.trim()).length() < 1 || label == null) {
            return 14;
        }
        int grade;
        //判断年级是否为空和长度
        if ((videoInfo.get("grade").trim()).length() < 1 ||
                (videoInfo.get("grade").trim()).length() > 1 || videoInfo.get("grade") == null) {
            return 15;
        } else {
            try {
                //成功就转int，若未非法字符，就转换失败，捕获异常
                grade = Integer.parseInt(grades);
            } catch (NumberFormatException e) {
                return 15;
            }
            //判断是否为已有年级
            if (grade != 0 && grade != 1 && grade != 2 && grade != 3) {
                return 15;
            }
        }
        //判断分类是否为空和长度
        if ((videoInfo.get("classify").trim()).length() < 1 ||
                (videoInfo.get("classify").trim()).length() > 1 || videoInfo.get("classify") == null) {
            return 16;
        } else {
            int classify;
            try {
                //成功就转int，若未非法字符，就转换失败，捕获异常
                classify = Integer.parseInt(videoInfo.get("classify"));
            } catch (NumberFormatException e) {
                return 16;
            }
            //判断是否为已有分类
            if (classify != 0 && classify != 1 && classify != 2) {
                return 16;
            }
        }
        return grade;
    }

    /**
     * 根据视频Id将数据库对应的状态（status）改回0
     *
     * @param videoIdMap map接收视频Id
     * @return 是否修改成功的Msg
     * @throws Exception
     * @author 牟杰
     */
    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "uploadOver", method = RequestMethod.POST)
    public Msg uploadOver(@RequestBody Map<String, String> videoIdMap) throws Exception {
        String videoName = "";
        String videoId = videoIdMap.get("videoId");
        if (StringUtil.isEmpty(videoId)) {
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "videoId参数不能为空");
        }
        Integer id = ParseUtil.parseInt(videoId);
        if (null == id) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "videoId只能为全数字的字符串，如：10006");
        }
        try {
            //获取修改的视频信息
            videoName = videoModifyService.getVideoById(id).getVideoName();
            Integer status = videoUploadService.uploadOver(id);
            //调用servce层方法执行业务，并返回数据库被修改了的行数
            if (0 == status) {
                return Msg.fail(ResultEnum.FAIL, "修改失败！");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            addLogInfoService.insertLogInfo("上传", "V" + videoId, videoName, 0);
        }
        addLogInfoService.insertLogInfo("上传", "V" + videoId, videoName, 1);
        return Msg.success("修改成功！");
    }
}
