package com.fdb.baselibrary.network;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.R;
import com.fdb.baselibrary.base.BaseBean;
import com.fdb.baselibrary.utils.L;
import com.fdb.baselibrary.utils.ToastUtil;
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
public abstract class NetSubscriber<T extends BaseBean> extends Subscriber<T> implements NetCallback<T> {
    private NetCallback<T> mNetCallback;

    public NetSubscriber() {

    }

    public NetSubscriber(NetCallback<T> netCallback) {
        mNetCallback = netCallback;
    }

    @Override
    final public void onNext(T t) {
        if (t.success) {
            onSuccess(t);
        } else {
            if (Constans.TOKEN_ERROR.equals(t.errorCode)) {
                onTokenError();
            } else {
                onDataError(new ApiException(t.errorCode, t.errorMessage));
            }
        }
    }

    @Override
    final public void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            L.P(e);
        }

        if (e instanceof HttpException) {  //HTTP错误
            onNetError();
        } else if (e instanceof ConnectException) {
            //网络连接失败
            onNetError();
        } else if (e instanceof UnknownHostException) {
            //网络请求失败
            onNetError();
        } else if (e instanceof SocketTimeoutException) {
            //网络连接超时
            onNetError();
        } else if (e instanceof JsonParseException) {
            //json解析错误
            onNetError();
        } else {
            onNetError();
        }
    }

    @Override
    final public void onStart() {
        if (mNetCallback != null) {
            mNetCallback.onPrepare();
        } else {
            onPrepare();
        }
    }

    @Override
    final public void onCompleted() {
        if (mNetCallback != null) {
            mNetCallback.onFinish();
        } else {
            onFinish();
        }
    }

    @Override
    public void onNetError() {
        if (mNetCallback != null) {
            mNetCallback.onNetError();
        } else {
            ToastUtil.s(R.string.network_connection_failed);
        }
    }

    @Override
    public void onDataError(ApiException error) {
        if (mNetCallback != null) {
            mNetCallback.onDataError(error);
        } else {
            if (error != null && error.message != null) {
                ToastUtil.s(error.message);
            } else {
                ToastUtil.s(R.string.data_error);
            }
        }
    }

    @Override
    public void onSuccess(T data) {
        if (mNetCallback != null) {
            mNetCallback.onSuccess(data);
        }
    }

    @Override
    public void onPrepare() {
        if (mNetCallback != null) {
            mNetCallback.onPrepare();
        }
    }

    @Override
    public void onFinish() {
        if (mNetCallback != null) {
            mNetCallback.onFinish();
        }
    }

    public void onTokenError() {
        //token的错误的默认UI操作, 如跳转登录页

    }

}
