package com.xiaomi.springboot.utils;

/**
 * Created by xiaolai on 2017/11/13.
 */
public class GlobleError extends Exception {
    private ErrrorInfoInteface infoInteface;

    public GlobleError(ErrrorInfoInteface errorInfo) {
        this.infoInteface = errorInfo;
    }

    public ErrrorInfoInteface getInfoInteface() {
        return infoInteface;
    }

    public void setInfoInteface(ErrrorInfoInteface infoInteface) {
        this.infoInteface = infoInteface;
    }
}
