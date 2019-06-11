package com.exam.service.admin.impl;

import com.exam.enums.RoleEnum;
import com.exam.service.admin.LoginService;
import com.exam.util.http.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 牟杰
 * @Project: video
 * @Package:com.exam.service.admin.impl
 * @date 2018/10/12 10:57
 * @description
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${accout}")
    private String accout;

    @Value("${password}")
    private String password;

    @Override
    public String loginCheck(String adminAccount, String adminPassword) {
        if(accout.equals(adminAccount)){
            if(password.equals(adminPassword)){
                RequestUtil.login(adminAccount,"管理员",RoleEnum.ADMIN);
                return null;
            }
            return "密码错误";
        }
            return "该账号不存在";
    }

    @Override
    public void loginOut() {
        RequestUtil.logout();
    }
}
