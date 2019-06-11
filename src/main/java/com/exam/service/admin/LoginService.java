package com.exam.service.admin;

/**
 * @author 牟杰
 * @Project: video
 * @Package:com.exam.service.admin
 * @date 2018/10/12 10:56
 * @description 管理员登陆的服务类
 **/
public interface LoginService {
    /**
     * 检查密码和账号是否与系统预设的密码账号符合，符合则返回true，否则返回false
     * @param adminAccount 管理员账号
     * @param adminPassword 管理员密码
     * @return true or false
     * @throws RuntimeException 往上抛出
     */
    String loginCheck(String adminAccount, String adminPassword)throws RuntimeException;

    /**
     * 注销登陆的服务
     * @throws RuntimeException 往上抛出
     */
    void loginOut() throws RuntimeException;
}
