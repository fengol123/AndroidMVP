package com.fdb.baselibrary.base;

public interface IBaseView {
    //显示进度中
    void showLoading();

    //隐藏进度
    void hideLoading();

    void showMessage(String msg);
}
