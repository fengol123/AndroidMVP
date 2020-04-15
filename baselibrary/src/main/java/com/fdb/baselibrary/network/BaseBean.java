package com.fdb.baselibrary.network;

/**
 * 网络请求接口的基类,根据后台接口而定
 * @param <T> 数据的bean
 */
public class BaseBean<T> {
    public int code;
    public String msg;
    /**
     * 数据
     */
    public T data;
}
