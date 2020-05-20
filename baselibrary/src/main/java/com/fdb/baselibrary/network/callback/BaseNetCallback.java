package com.fdb.baselibrary.network.callback;

import com.fdb.baselibrary.bean.BaseDisposable;

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

    public void onPrepare(BaseDisposable disposable){

    }

    @Override
    public void onFinish(BaseDisposable disposable) {

    }
}
