package com.fdb.baselibrary.network;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/15.
 */
public interface RxCallback<T> {
    /**
     * 网络问题
     * 网络超时
     * json数据转换异常
     */
    public void onNetError();

    /**
     * 后台返回错误信息或者错误码
     */
    public void onDataError(DataErrorBean error);

    /**
     * 后台返回了200状态码，并且数据转换正常
     */
    public void onSuccess(T data);

    /**
     * 网络请求开始
     */
    public void onStart();

    /**
     * 网络请求结束，无论成功或者失败
     */
    public void onFinish();
}
