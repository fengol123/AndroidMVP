package com.fdb.baselibrary.network;

/**
 * 请求错误异常类
 */
public class ApiException extends RuntimeException {
    private int code; //错误码(后台返回)
    private String message; //错误信息

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(int status, String message) {
        super(message);
        this.code = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int status) {
        this.code = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
