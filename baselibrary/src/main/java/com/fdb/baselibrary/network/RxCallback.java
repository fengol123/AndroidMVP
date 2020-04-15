package com.fdb.baselibrary.network;

import com.google.gson.JsonParseException;
import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.R;
import com.fdb.baselibrary.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;



/**
 * rxjava订阅观察类 已实现功能如下:
 * <pre>
 *     1.常见异常捕捉(包括null异常的捕捉)
 *     2.成功时返回数据
 * </pre>
 */
public abstract class RxCallback<T> implements Callback<T> {
//    @Override
//    public void onSubscribe(Disposable d) {
//        onStart();
//    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }

        if (e instanceof ConnectException) {
            ToastUtil.s(R.string.network_connection_failed);
        } else if (e instanceof UnknownHostException) {
            ToastUtil.s(R.string.network_request_failed);
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.s(R.string.network_connection_timeout);
        } else if (e instanceof JsonParseException) {
            ToastUtil.s(R.string.json_failed);
        } else if (e instanceof RxJava2NullException) {//RxJava2不能发送null
            ToastUtil.s(R.string.data_null);
        } else if (e instanceof ApiException) {
            onApiException((ApiException) e);
        } else {
            ToastUtil.s(e.getMessage());
        }
        onFinish();
    }

//    @Override
//    public void onComplete() {
//        onFinish();
//    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onApiException(ApiException e){
        ToastUtil.s(e.getMessage());
    }
}
