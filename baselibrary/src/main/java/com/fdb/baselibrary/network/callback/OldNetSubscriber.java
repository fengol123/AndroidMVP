package com.fdb.baselibrary.network.callback;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.bean.BaseDisposable;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.bean.HTTPErrorBean;
import com.fdb.baselibrary.bean.OldBaseBean;
import com.fdb.baselibrary.utils.JsonUtils;
import com.fdb.baselibrary.utils.L;
import com.fdb.baselibrary.utils.StringUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * Desc 旧接口的网络监听处理类
 * Author xieguofeng
 * Date   2020/4/15.
 */
public class OldNetSubscriber<T extends OldBaseBean> extends DisposableObserver<T> {
    private NetCallback<T> mNetCallback;
    private CompositeDisposable mCompositeDisposable;
    private BaseDisposable mBaseDisposable = new BaseDisposable(this, new BaseDisposable.OnDisposeListener() {
        @Override
        public void onDispose() {
            mCompositeDisposable.delete(OldNetSubscriber.this);
        }
    });

    public OldNetSubscriber(CompositeDisposable compositeDisposable, NetCallback<T> netCallback) {
        mCompositeDisposable = compositeDisposable;
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
            mNetCallback.onDataError(new DataErrorBean(null, e.getMessage()));
        }

        mNetCallback.onFinish(mBaseDisposable);
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
                        mNetCallback.onDataError(new DataErrorBean(null, httpErrorBean.message));
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
        if(mCompositeDisposable != null){
            mCompositeDisposable.add(this);
        }
        mNetCallback.onPrepare(mBaseDisposable);
    }

    @Override
    public void onComplete() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.delete(this);
        }
        mNetCallback.onFinish(mBaseDisposable);
    }
}
