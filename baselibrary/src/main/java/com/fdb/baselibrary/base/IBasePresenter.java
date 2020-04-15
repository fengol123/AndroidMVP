package com.fdb.baselibrary.base;

public interface IBasePresenter<T> {

    void attachView(T view);

    void detachView();
}
