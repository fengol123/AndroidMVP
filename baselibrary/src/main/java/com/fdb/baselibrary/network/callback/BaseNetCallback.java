package com.fdb.baselibrary.network.callback;

import io.reactivex.disposables.Disposable;

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

    public void onPrepare(Disposable disposable){

    }

    @Override
    public void onFinish(Disposable disposable) {

    }
}
