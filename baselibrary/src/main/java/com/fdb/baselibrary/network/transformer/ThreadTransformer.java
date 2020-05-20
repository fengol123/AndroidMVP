package com.fdb.baselibrary.network.transformer;


import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * RxJava2 转换器 已实现功能有：
 * <pre>
 * 1.任务开始显示loadingdialog, 任务结束隐藏loadingdialog
 * </pre>
 */
public class ThreadTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

