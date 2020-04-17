package com.fdb.baselibrary.network;

import rx.Subscription;

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
    public void onPrepare(Subscription subscription) {

    }

    @Override
    public void onFinish() {

    }
}
