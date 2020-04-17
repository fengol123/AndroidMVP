package com.fdb.baselibrary.network.transformer;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava2 转换器 已实现功能有：
 * <pre>
 * 1.任务开始显示loadingdialog, 任务结束隐藏loadingdialog
 * </pre>
 */
public class ThreadTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
