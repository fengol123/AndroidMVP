package com.fdb.baselibrary.network;

/**
 * 请求错误异常类
 */
public class ApiException extends RuntimeException {
    public String code; //错误码(后台返回) ， 服务端的旧框架没有code
    public String message; //错误信息

    public ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
