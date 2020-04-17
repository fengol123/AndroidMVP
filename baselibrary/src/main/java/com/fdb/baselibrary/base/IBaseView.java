package com.fdb.baselibrary.base;

import rx.Subscription;

public interface IBaseView {
    //显示进度中
    void showLoading(Subscription subscription);

    //隐藏进度
    void hideLoading();

    void showMessage(String msg);
}
