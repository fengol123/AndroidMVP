package com.fdb.baselibrary.bean;

/**
 * Desc 新接口的网络回调实体基类
 * Author xieguofeng
 * Date   2020/4/15.
 */
public class BaseBean<T> {
    public boolean success;
    public String message;
    public String errorCode;
    public String errorMessage;
    public int totalCount;
    public int pageCount;
    public T data;

}
