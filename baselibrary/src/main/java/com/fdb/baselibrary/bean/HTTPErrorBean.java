package com.fdb.baselibrary.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Desc 网络错误的封装实体
 * Author xieguofeng
 * Date   2020/4/15.
 */
public class HTTPErrorBean {
    @SerializedName(value = "message", alternate = "Message")
    public String message;
}
