package com.fdb.baselibrary.network;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.R;
import com.fdb.baselibrary.utils.ToastUtil;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class CommonNetCallbackImpl<T> implements NetCallback<T> {

    @Override
    public void onNetError() {
        ToastUtil.s(R.string.network_connection_failed);
    }

    @Override
    public void onDataError(@NonNull ApiException error) {
        if (error.message != null) {
            ToastUtil.s(error.message);
        } else {
            ToastUtil.s(R.string.data_error);
        }
    }

    @Override
    public void onSuccess(@NonNull T data) {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onFinish() {

    }
}
