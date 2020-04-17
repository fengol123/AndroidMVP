package com.fdb.baselibrary.bean;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
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
