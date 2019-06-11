package com.exam.aspect;

import com.exam.bean.Msg;
import com.exam.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.aspect
 * @date 2018/9/18 16:25
 * @description 控制器增强
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionAspect {
    /** Log4j日志处理(@author: rico) */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Msg handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("could_not_read_json...", e);
        return Msg.fail(ResultEnum.EXCEPTION,"400");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Msg handleValidationException(MethodArgumentNotValidException e) {
        log.error("parameter_validation_exception...", e);
        return Msg.fail(ResultEnum.EXCEPTION,"400");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Msg handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return Msg.fail(ResultEnum.EXCEPTION,"400");
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Msg handlerNotFoundException(NoHandlerFoundException e) {
        log.error("请求的资源不可用", e);
        return Msg.fail(ResultEnum.EXCEPTION,"404");
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Msg handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("request_method_not_supported...", e);
        return Msg.fail(ResultEnum.EXCEPTION,"500");
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
    public Msg handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content_type_not_supported...", e);
        return Msg.fail(ResultEnum.EXCEPTION,"500");
    }
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Msg handleException(Exception e) {
        log.error("Internal Server Error...", e);
        return Msg.fail(ResultEnum.EXCEPTION,"500");
    }
//    /**
//     * 500 - Internal Server Error
//     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(TokenException.class)
//    public Msg handleTokenException(Exception e) {
//        log.error("Token is invaild...", e);
//        return Msg.fail(500);
//    }


}
