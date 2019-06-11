package com.exam.enums;

/**
 * @author 痞老板
 * @Project: exam
 * @Package:com.exam.enums
 * @date 2018/8/29 11:04
 * @description 注意！ 现在成功为1
 **/
public enum ResultEnum {
    //账号或密码错误
    FAIL(0),
     //成功
   SUCCESS(1),
    //无操作权限
    NO_AUTHORITY(2),
    //异常
    EXCEPTION(3),
    //参数不正确
    PARAM_NOT_SUIT(4),
    //参数不能为空
    PARAM_NOT_EMPTY(5),
    //文件大小不正常
    FILE_SIZE_NOT_SUIT(6),
    //文件类型不正确
    FILE_TYPE_NOT_SUIT(7),
    //Token错误
    TOKEN_ERRO(8)
    ;




    private int code;

    private ResultEnum(int code) {
        this.code = code;
    }

    public int value() {
        return this.code;
    }
}
