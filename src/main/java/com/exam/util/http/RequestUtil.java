package com.exam.util.http;

import com.exam.config.BaseStatic;
import com.exam.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 痞老板
 * @Project: exam
 * @Package:com.exam.util.http
 * @date 2018/8/31 10:59
 * @description 登录、注销等session和请求的操作
 **/
public class RequestUtil {
    private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

    /**登录将账号权限放入*/
    public static void login(String accout,String name,RoleEnum role){
        HttpServletRequest request=getRequest();
        HttpServletResponse response=getResponse();
        Cookie nameCookie=new Cookie(BaseStatic.COOKIE_NAME,name);
        nameCookie.setPath(BaseStatic.COOKIE_PATH);
        Cookie accountCookie=new Cookie(BaseStatic.COOKIE_ACCOUNT,accout);
        accountCookie.setPath(BaseStatic.COOKIE_PATH);
        response.addCookie(nameCookie);
        response.addCookie(accountCookie);
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_ACCOUT,accout);
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_NAME,name);
        request.getSession().setAttribute(BaseStatic.KEY_SESSION_ROLE,role);
       }
       /**将角色常用信息放入*/
    public static void loginInfo(Map<String,Object> info){
        getRequest().getSession().setAttribute(BaseStatic.KEY_SESSION_INFO,info);
    }
    @SuppressWarnings("unchecked")
    public static Map<String,Object> getInfo(){
        return (Map<String, Object>)getRequest().getSession().getAttribute(BaseStatic.KEY_SESSION_INFO);
    }
    public static void logout(){
        HttpServletRequest request=getRequest();
        HttpServletResponse response=getResponse();
        Cookie nameCookie=new Cookie(BaseStatic.COOKIE_NAME,(String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_NAME));
        nameCookie.setMaxAge(0);
        nameCookie.setPath(BaseStatic.COOKIE_PATH);
        Cookie accountCookie=new Cookie(BaseStatic.COOKIE_ACCOUNT,(String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_ACCOUT));
        accountCookie.setMaxAge(0);
        accountCookie.setPath(BaseStatic.COOKIE_PATH);
        response.addCookie(nameCookie);
        response.addCookie(accountCookie);
        request.getSession().invalidate();
    }

    public static RoleEnum getRole(){
        Object obj=getRequest().getSession().getAttribute(BaseStatic.KEY_SESSION_ROLE);
        log.info("role="+obj);
        if (obj==null){return null;}
        return (RoleEnum)obj;
    }
    public static String getToken(){
        HttpServletRequest req = getRequest();
        return (String) req.getSession().getAttribute(BaseStatic.KEY_SESSION_TOKEN);

    }
    public static void createToken(String token){
        HttpServletRequest req = getRequest();
        req.getSession().setAttribute(BaseStatic.KEY_SESSION_TOKEN,token);
    }
    public static boolean isAjax() {
        return "XMLHttpRequest".equals(getRequest().getHeader("X-Requested-With")) || "XMLHttpRequest".equals(getRequest().getParameter("X-Requested-With"));
    }

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    public static HttpServletResponse getResponse(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
