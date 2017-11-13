package com.xiaomi.springboot.utils;

/**
 * Created by xiaolai on 2017/11/13.
 */
public enum  ErrorEnum  implements ErrrorInfoInteface{
    SUCCESS("1001","未知错误"),
    FAILD("1002","service not found");

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
