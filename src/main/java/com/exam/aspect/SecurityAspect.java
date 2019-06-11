package com.exam.aspect;

import com.exam.annotation.Security;
import com.exam.bean.Msg;
import com.exam.enums.ResultEnum;
import com.exam.enums.RoleEnum;

import com.exam.util.http.RequestUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.aspect
 * @date 2018/9/19 15:56
 * @description
 **/
@Aspect
public class SecurityAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Pointcut("execution(* com.exam.controller..*.*(..))")
    public void pointCut(){}
    @Around(value = "pointCut()&&@annotation(security)")
    public Object RoleAop(ProceedingJoinPoint point,Security security)  throws Throwable{
//        if (security.checkToken()){
//            String s1=RequestUtil.getToken();
//            log.info("sessionToken:"+s1);
//            String s2=CodeUtil.getToken();
//            log.info("requestToken:"+s2);
//            if (!s1.equals(s2)|| StringUtil.isEmpty(s1)||StringUtil.isEmpty(s2)){
//                return Msg.fail(ResultEnum.TOKEN_ERRO);
//            }
//        }
//        if (security.createToken()){
//            RequestUtil.createToken(CodeUtil.createToken());
//            log.info("createtoken:"+RequestUtil.getToken());
//        }
        RoleEnum[] roles=security.roles();
        if (roles.length>0){
            if (roles[0]==RoleEnum.LOG){
                    return  point.proceed();
            }
         for(RoleEnum role:roles){
                if (role==RequestUtil.getRole()){
                        return point.proceed();
                }
         }
        }
        return Msg.fail(ResultEnum.NO_AUTHORITY);
    }

}
