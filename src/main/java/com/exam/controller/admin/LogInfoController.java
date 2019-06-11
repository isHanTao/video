package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.bean.admin.LogBean;
import com.exam.config.RegexStatic;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.impl.LogInfoServiceImpl;
import com.exam.util.type.ParseUtil;
import com.exam.util.type.StringUtil;
import com.exam.util.type.TypeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author: 李静松
 * @Date: 2018-10-11 14:31
 * @Description: 查询日志
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class LogInfoController {

    @Autowired
    LogInfoServiceImpl logInfoService;

    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping("/getLogInfo")

    public Msg queryLogInfo(@RequestBody Map<String, String> timeMap) throws Exception {
        Integer pageSize = TypeUtil.createInt(3);
        String timeBegin = timeMap.get("timeBegin");
        String timeEnd = timeMap.get("timeEnd");
        String pageNow = timeMap.get("pageNum");
        if (StringUtil.isEmpty(timeBegin) || StringUtil.isEmpty(timeBegin) || StringUtil.isEmpty(pageNow)) {
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "参数不能为空");
        }
        if (!Pattern.compile(RegexStatic.NUMBER).matcher(timeBegin).matches() || !Pattern.compile(RegexStatic.NUMBER).matcher(timeEnd).matches()
                || !Pattern.compile(RegexStatic.NUMBER).matcher(pageNow).matches()) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "参数格式不对");
        }
        Integer pageNum = ParseUtil.parseInt(pageNow);
        PageHelper.startPage(pageNum, pageSize);
        List<LogBean> logList = logInfoService.findLogInfo(timeBegin, timeEnd);
        PageInfo<LogBean> pageInfo = new PageInfo(logList, 5);
        return Msg.success(pageInfo);


    }
}
