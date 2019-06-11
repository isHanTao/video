package com.exam.controller;



import com.exam.annotation.Security;
import com.exam.bean.Msg;


import com.exam.enums.RoleEnum;
import com.exam.exception.RollbackException;
import com.exam.service.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * 需要加上模块url前缀
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private HttpServletRequest request;
    /**输出使用日志输出，禁止使用System.out*/
    private Logger log = LoggerFactory.getLogger(getClass());


    //获取下一层实例
    @Autowired
    private DemoService service;

//    @Autowired
//    private UploadService uploadService;

    /**
     * 跳转到页面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "";
    }

    /**
     * 返回json数据的用法
     * 异常上抛
     */
    @RequestMapping("testJson")
    public Msg testJson() throws RollbackException{
        Msg result = Msg.success();
        service.test("2");
        return result;
    }
    @Security(roles = RoleEnum.LOG)
    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public Msg test(){
        return Msg.success();
    }
    @Security(roles = RoleEnum.LOG)
    @RequestMapping(value = "test1",method = RequestMethod.GET)
    @ResponseBody
    public Msg test1(){
        return Msg.success();
    }

//    /**
//     * 权限注解使用方法
//     *
//     *
//     */
//    @RequestMapping("/testAuthority")
//    @Security
//    public String testAuthority() {
//        return "";
//    }


//    /**
//     * 文件上传
//     *
//     * @param file
//     * @return
//     */
//    @RequestMapping("/fileUpload")
//    public String fileUpload(MultipartFile file) {
//        //此为采用默认的文件重命名方式
//        String result = uploadService.upload(file, request);
//
//        //这样为自定义文件重命名方式
//        File result2 = new File(uploadService.upload(file, request, new FileRename() {
//            @Override
//            public String rename(MultipartFile file, HttpServletRequest request) {
//                return "test.jpg";
//            }
//        }));
//        File result3=new File(uploadService.upload(file,request,(file1,result1)->{
//            return "test.jpg";
//        }));
//        //文件上传失败，多半是验证不通过
//        if (result == null) {
//            //获取错误信息
//            String message = uploadService.getMessage();
//        }
//
//        return "";
//    }
}
