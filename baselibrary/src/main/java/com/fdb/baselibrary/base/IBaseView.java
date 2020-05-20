package com.fdb.baselibrary.base;

import io.reactivex.disposables.Disposable;

public interface IBaseView {
    //显示进度中
    void showLoading(Disposable disposable);

    //隐藏进度
    void hideLoading();

    void showMessage(String msg);
}
