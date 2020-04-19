package com.fdb.baselibrary.network.callback;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.R;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.utils.StringUtils;
import com.fdb.baselibrary.utils.ToastUtil;

import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
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

    @Override
    public void onPrepare(Subscription subscription) {

    }

    @Override
    public void onFinish() {

    }
}
