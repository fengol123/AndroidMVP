package com.fdb.baselibrary.bean;

/**
 * Desc 网络错误的封装实体
 * Author xieguofeng
 * Date   2020/4/15.
 */
public class DataErrorBean{
    public String code; //错误码(后台返回) ， 服务端的旧框架没有code
    public String message; //错误信息

    public DataErrorBean(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
