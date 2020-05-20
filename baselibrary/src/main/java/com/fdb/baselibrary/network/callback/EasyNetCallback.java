package com.fdb.baselibrary.network.callback;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.R;
import com.fdb.baselibrary.bean.BaseDisposable;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.utils.StringUtils;
import com.fdb.baselibrary.utils.ToastUtil;

/**
 * Desc 简单网络监听,发生网络错误会吐司
 * Author xieguofeng
 * Date   2020/4/15.
 */
public class EasyNetCallback<T> extends BaseNetCallback<T> {
    public EasyNetCallback() {

    }

    @Override
    public void onNetError() {
        ToastUtil.s(StringUtils.getString(R.string.network_connection_failed));
    }

    @Override
    public void onDataError(@NonNull DataErrorBean error) {
        if (error.message != null) {
            ToastUtil.s(error.message);
        } else {
            ToastUtil.s(StringUtils.getString(R.string.data_error));
        }
    }

    @Override
    public void onSuccess(@NonNull T data) {

    }

    public void onPrepare(BaseDisposable disposable){

    }

    @Override
    public void onFinish(BaseDisposable disposable) {

    }
}
