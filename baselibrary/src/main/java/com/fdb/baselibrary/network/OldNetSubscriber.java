package com.fdb.baselibrary.network;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.base.OldBaseBean;
import com.fdb.baselibrary.utils.JsonUtils;
import com.fdb.baselibrary.utils.L;
import com.fdb.baselibrary.utils.StringUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * rxjava订阅观察类 已实现功能如下:
 * <pre>
 *     1.常见异常捕捉(包括null异常的捕捉)
 *     2.成功时返回数据
 * </pre>
 */
public class OldNetSubscriber<T extends OldBaseBean> extends Subscriber<T> {
    private NetCallback<T> mNetCallback;

    public OldNetSubscriber(@NonNull NetCallback<T> netCallback) {
        mNetCallback = netCallback;
    }

    @Override
    final public void onNext(T t) {
        mNetCallback.onSuccess(t);
    }

    @Override
    final public void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            L.P(e);
        }

        if (e instanceof HttpException) {             //HTTP错误
            getHTTPErrorMessage((HttpException) e);
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
            mNetCallback.onNetError();
        }

        mNetCallback.onFinish();
    }

    /**
     * 获取HTTP的错误信息
     *
     * @param e
     */
    private void getHTTPErrorMessage(HttpException e) {
        try {
            //兼容后台旧框架, 获取错误信息
            int code = e.code();
            if (code == 401) {
                mNetCallback.onTokenError();
                return;
            }

            ResponseBody responseBody = e.response().errorBody();
            if (responseBody != null) {
                String string = responseBody.string();
                if (!StringUtils.isEmpty(string)) {
                    HTTPErrorBean httpErrorBean = JsonUtils.jsonToBean(string, HTTPErrorBean.class);
                    if (httpErrorBean != null && !StringUtils.isEmpty(httpErrorBean.message)) {
                        mNetCallback.onDataError(new ApiException(null, httpErrorBean.message));
                        return;
                    }
                }
            }
        } catch (IOException ex) {
            L.P(ex);
        }

        mNetCallback.onNetError();
    }

    @Override
    final public void onStart() {
        mNetCallback.onPrepare();
    }

    @Override
    final public void onCompleted() {
        mNetCallback.onFinish();
    }
}
