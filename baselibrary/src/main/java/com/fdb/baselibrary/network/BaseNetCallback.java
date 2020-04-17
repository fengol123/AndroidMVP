package com.fdb.baselibrary.network;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public abstract class BaseNetCallback<T> implements NetCallback<T> {
    @Override
    public void onTokenError() {
        //统一处理token过期

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onFinish() {

    }
}
