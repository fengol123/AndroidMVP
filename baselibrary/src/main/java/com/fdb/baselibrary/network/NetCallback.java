package com.fdb.baselibrary.network;

import android.support.annotation.NonNull;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/15.
 */
public interface NetCallback<T> {
    /**
     * 网络问题
     * 网络超时
     */
    public void onNetError();

    /**
     * 后台返回错误信息或者错误码
     * json数据转换异常
     */
    public void onDataError(@NonNull ApiException error);

    /**
     * token失效
     */
    public void onTokenError();

    /**
     * 后台返回了200状态码，并且数据转换正常
     */
    public void onSuccess(@NonNull T data);

    /**
     * 网络请求开始
     */
    public void onPrepare();

    /**
     * 网络请求结束，无论成功或者失败
     */
    public void onFinish();
}
