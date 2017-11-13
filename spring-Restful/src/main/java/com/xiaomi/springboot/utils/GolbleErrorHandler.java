package com.xiaomi.springboot.utils;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by xiaolai on 2017/11/13.
 */
@RestControllerAdvice
public class GolbleErrorHandler {
    @ExceptionHandler(value = GlobleError.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request,GlobleError globleError){
        ErrrorInfoInteface infoInteface=globleError.getInfoInteface();
        ResultBody resultBody=new ResultBody(infoInteface);
        return  resultBody;

    }
}
