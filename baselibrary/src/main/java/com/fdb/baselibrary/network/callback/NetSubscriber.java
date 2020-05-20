package com.fdb.baselibrary.network.callback;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.Constans;
import com.fdb.baselibrary.bean.BaseBean;
import com.fdb.baselibrary.bean.BaseDisposable;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.utils.L;
import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;


/**
 * rxjava订阅观察类 已实现功能如下:
 * <pre>
 *     1.常见异常捕捉(包括null异常的捕捉)
 *     2.成功时返回数据
 * </pre>
 */
public class NetSubscriber<T extends BaseBean> extends DisposableObserver<T> {
    private NetCallback<T> mNetCallback;
    private CompositeDisposable mCompositeDisposable;
    private BaseDisposable mBaseDisposable = new BaseDisposable(this, new BaseDisposable.OnDisposeListener() {
        @Override
        public void onDispose() {
            mCompositeDisposable.delete(NetSubscriber.this);
        }
    });

    public NetSubscriber(CompositeDisposable compositeDisposable, NetCallback<T> netCallback) {
        mCompositeDisposable = compositeDisposable;
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

        mNetCallback.onFinish(mBaseDisposable);
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
