package com.fdb.baselibrary.base;

import com.fdb.baselibrary.bean.BaseDisposable;

public interface IBaseView {
    //显示进度中
    void showLoading(BaseDisposable disposable);

    //隐藏进度
    void hideLoading();

    void showMessage(String msg);
}
