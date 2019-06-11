package com.exam.controller.admin;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.config.RegexStatic;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;
import com.exam.service.admin.AddLogInfoService;
import com.exam.service.admin.LoginService;
import com.exam.util.type.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: video
 * @Package: com.exam.controller.admin
 * @Author: mj
 * @Date: 2018-10-11 14:32
 * @Description: 登陆退出的控制类
 * @Param:
 **/

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AddLogInfoService addLogInfoService;

    /**
     * 检查登陆，符合条件则给与登陆，否则返回错误信息
     * 
     * @param adminMap 接收管理员的账号和密码
     * @return Msg 登陆是否成功的信息
     * @throws Exception
     */
    @Security(roles = RoleEnum.LOG)
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public Msg loginCheck(@RequestBody Map<String, String> adminMap) throws Exception {
        if (null == adminMap) {
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "参数不能为空");
        }
        // 检查账号是否有为空
        String adminAccount = adminMap.get("adminAccount");
        if (StringUtil.isEmpty(adminAccount)) {
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "账号不能为空");
        }
        // 检查密码是否有为空
        String adminPassword = adminMap.get("adminPassword");
        if (StringUtil.isEmpty(adminPassword)) {
            return Msg.fail(ResultEnum.PARAM_NOT_EMPTY, "密码不能为空");
        }
        // 检查密码是否有中文
        if (regexCheck(RegexStatic.CHINESE, adminPassword)) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "密码不能含有中文");
        }
        // 检查密码是否有是6-16位的字母或数字
        String regex = "^[0-9a-zA-Z]{6,16}$";
        if (!regexCheck(regex, adminPassword)) {
            return Msg.fail(ResultEnum.PARAM_NOT_SUIT, "密码只能是6-16位的数字或字母");
        }
        // 与系统预设的登陆信息比对
        String msg = loginService.loginCheck(adminAccount, adminPassword);
        if (null == msg) {
            try {
                addLogInfoService.insertLogInfo("登陆", "+", "", 1);
                return Msg.success("登陆成功");
            } catch (RuntimeException e) {
                addLogInfoService.insertLogInfo("登陆", "+", "", 0);
                return Msg.fail(ResultEnum.FAIL,"登陆异常");
            }

        } else {
            addLogInfoService.insertLogInfo("登陆", "+", "", 0);
            return Msg.fail(ResultEnum.NO_AUTHORITY, msg);
        }
    }

    /**
     * 注销登陆
     * 
     * @throws Exception
     */
    @Security(roles = RoleEnum.ADMIN)
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Msg loginOut() throws Exception {
        try {
            loginService.loginOut();
            addLogInfoService.insertLogInfo("注销", "-", "", 1);
            return Msg.success("注销成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            addLogInfoService.insertLogInfo("注销", "-", "", 0);
            return Msg.fail(ResultEnum.FAIL,"注销异常");
        }

    }

    /**
     * 检查字符串是否符合该正则
     * 
     * @param regex 正则表达式
     * @param value 待检查的字符串
     * @return 符合返回true，否则为false
     */
    private boolean regexCheck(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
