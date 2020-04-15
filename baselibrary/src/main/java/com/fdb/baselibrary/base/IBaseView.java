package com.fdb.baselibrary.base;

import android.content.Context;

public interface IBaseView {
    Context getContext();

    //显示进度中
    void showLoading();

    //隐藏进度
    void hideLoading();

    void showMessage(String msg);
}
