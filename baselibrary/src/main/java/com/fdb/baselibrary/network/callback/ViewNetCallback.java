package com.fdb.baselibrary.network.callback;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.R;
import com.fdb.baselibrary.base.IBaseView;
import com.fdb.baselibrary.bean.BaseDisposable;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.utils.StringUtils;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class ViewNetCallback<T> extends BaseNetCallback<T> {
    private final IBaseView mBaseView;


    public ViewNetCallback(IBaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void onNetError() {
        mBaseView.showMessage(StringUtils.getString(R.string.network_connection_failed));
    }

    @Override
    public void onDataError(@NonNull DataErrorBean error) {
        if (error.message != null) {
            mBaseView.showMessage(error.message);
        } else {
            mBaseView.showMessage(StringUtils.getString(R.string.data_error));
        }
    }

    @Override
    public void onSuccess(@NonNull T data) {

    }

    @Override
    public void onPrepare(BaseDisposable disposable){
        mBaseView.showLoading(disposable);
    }

    @Override
    public void onFinish(BaseDisposable disposable) {
        mBaseView.hideLoading();
    }
}
