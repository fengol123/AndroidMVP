package com.fdb.baselibrary.network.callback;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.Constans;
import com.fdb.baselibrary.bean.BaseBean;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.utils.L;
import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * rxjava订阅观察类 已实现功能如下:
 * <pre>
 *     1.常见异常捕捉(包括null异常的捕捉)
 *     2.成功时返回数据
 * </pre>
 */
public class NetSubscriber<T extends BaseBean> extends Subscriber<T> {
    private NetCallback<T> mNetCallback;

    public NetSubscriber(@NonNull NetCallback<T> netCallback) {
        mNetCallback = netCallback;
    }

    @Override
    final public void onNext(T t) {
        if (t.success) {
            mNetCallback.onSuccess(t);
        } else {
            if (Constans.TOKEN_ERROR.equals(t.errorCode)) {
                mNetCallback.onTokenError();
            } else {
                mNetCallback.onDataError(new DataErrorBean(t.errorCode, t.errorMessage));
            }
        }
    }

    @Override
    final public void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            L.P(e);
        }

        if (e instanceof HttpException) {  //HTTP错误
            mNetCallback.onNetError();
        } else if (e instanceof ConnectException) {
            //网络连接失败
            mNetCallback.onNetError();
        } else if (e instanceof UnknownHostException) {
            //网络请求失败
            mNetCallback.onNetError();
        } else if (e instanceof SocketTimeoutException) {
            //网络连接超时
            mNetCallback.onNetError();
        } else if (e instanceof JsonParseException) {
            //json解析错误
            mNetCallback.onNetError();
        } else {
            mNetCallback.onDataError(new DataErrorBean(null, e.getMessage()));
        }
    }

    @Override
    final public void onStart() {
        mNetCallback.onPrepare(this);
    }

    @Override
    final public void onCompleted() {
        mNetCallback.onFinish();
    }

}
