package com.fdb.baselibrary.network;

import com.google.gson.annotations.SerializedName;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/15.
 */
public class HTTPErrorBean {
    @SerializedName(value = "message", alternate = "Message")
    public String message;
}
