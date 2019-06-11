package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.bean.admin.VideoBean;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.VideoSelectService;
import com.exam.util.type.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author: 王曦
 * @Date: 2018-10-11 14:30
 * @Description:
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class VideoSelectController {
    @Autowired
    VideoSelectService videoSelectService;



    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/selectVideoByName", method = RequestMethod.POST)
    public Msg selectVideoByName(@RequestBody Map<String, String> map) throws RuntimeException {
        String msg = "成功";
        String name = map.get("name");
        if (StringUtil.isEmpty(name)) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT,"参数错误");
        }
        int pageNum = 1;
        try {
            pageNum = Integer.parseInt(map.get("pageNum"));
        }catch (NumberFormatException | NullPointerException e){
            msg = "参数不完整!";
        }
        PageHelper.startPage(pageNum, 5);
        List<VideoBean> videos = videoSelectService.selectVideoByName(name);
        PageInfo pageInfo = new PageInfo(videos);
        return  Msg.success(msg, pageInfo);
    }




    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/selectVideoByInfo", method = RequestMethod.POST)
    public Msg selectVideosByInfo ( @RequestBody Map<String, String> map) throws Exception {
        String msg = "成功";
        int pageNum = 1;
        String classify = "";
        String label = "";
        String grade = "";
        try {
            classify = map.get("classify").toString();
            label = map.get("label").toString();
            grade = map.get("grade").toString();
            pageNum =Integer.parseInt(map.get("pageNum"));
        }catch (NullPointerException | NumberFormatException e){
            msg = "参数不完整!";
        }
        String classifyRegex = "[0-2]";
        String gradeRegex = "[0-3]";
        if (StringUtil.isNotEmpty(classify)) {
            if (!classify.matches(classifyRegex)) {
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT,"参数错误");
            }
        }
        if (StringUtil.isNotEmpty(grade)) {
            if (!grade.matches(gradeRegex)) {
                return Msg.fail(ResultEnum.PARAM_NOT_SUIT,"参数错误");
            }
        }
        PageInfo pageInfo = videoSelectService.selectVideoByInfo(classify, label, grade, pageNum);
        return  Msg.success(msg, pageInfo);
    }

}
