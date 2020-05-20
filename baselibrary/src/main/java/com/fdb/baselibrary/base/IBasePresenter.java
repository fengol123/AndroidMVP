package com.fdb.baselibrary.base;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface IBasePresenter<T> {
    /**
     * 将一部分观察者取消订阅
     * @param disposables
     */
    void disPose(List<Disposable> disposables);

    void attachView(T view);

    void detachView();
}
