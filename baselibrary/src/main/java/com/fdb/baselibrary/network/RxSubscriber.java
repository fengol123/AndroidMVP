package com.fdb.baselibrary.network;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.R;
import com.fdb.baselibrary.utils.ToastUtil;
import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;


/**
 * rxjava订阅观察类 已实现功能如下:
 * <pre>
 *     1.常见异常捕捉(包括null异常的捕捉)
 *     2.成功时返回数据
 * </pre>
 */
public abstract class RxSubscriber<T> extends Subscriber<T> implements RxCallback{
    @Override
    final public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    final public void onError(Throwable e) {
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
        } else if (e instanceof RxJavaNullException) {//RxJava2不能发送null
            ToastUtil.s(R.string.data_null);
        } else if (e instanceof ApiException) {
//            onApiException((ApiException) e);
        } else {
            ToastUtil.s(e.getMessage());
        }
//        onFinish();
    }

    @Override
    public void onCompleted() {

    }


    //    @Override
//    public void onComplete() {
//        onFinish();
//    }


//    @Override
//    public void onApiException(ApiException e){
//        ToastUtil.s(e.getMessage());
//    }
//
//    /*** 请求网络开始前，UI线程 */
//    void onStart();
//

//    /*** 对返回数据进行操作的回调， UI线程 */
//    public abstract void onSuccess(@Nullable T data);

//
//
//    /*** 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程 */
//    void onError(Throwable throwable);
//
//    /*** 请求网络结束后，UI线程 */
//    void onFinish();
//
//    /*** 当网络接口返回错误code时回调, UI线程 */
//    void onApiException(ApiException e);
}
