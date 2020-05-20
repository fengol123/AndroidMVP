package com.fdb.baselibrary.network.callback;

import com.fdb.baselibrary.bean.BaseDisposable;

/**
 * Desc 网络监听类, 此处做统一的token错误处理
 * Author xieguofeng
 * Date   2020/4/15.
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
