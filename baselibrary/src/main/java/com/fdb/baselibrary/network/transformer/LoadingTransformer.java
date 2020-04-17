package com.fdb.baselibrary.network.transformer;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * RxJava2 转换器 已实现功能有：
 * <pre>
 * 1.任务开始显示loadingdialog, 任务结束隐藏loadingdialog
 * </pre>
 */
public class LoadingTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //开始时执行，显示loadingDialog

                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        //结束时执行，隐藏loadingDialog

                    }
                });

    }

    //    private IBaseDisplay mView;
    //
    //    public LoadingTransformer(IBaseDisplay mView) {
    //        this.mView = mView;
    //    }
    //
    //    @Override
    //    public ObservableSource<T> apply(Observable<T> upstream) {
    //        return upstream
    //                .doOnSubscribe(disposable -> {
    //                    if (mView != null) {
    //                        mView.showProgressDialog();
    //                    }
    //                })
    //                .doFinally(() -> {
    //                    if (mView != null) {
    //                        mView.hideProgressDialog();
    //                    }
    //                });
    //    }

    //    public static class RxTransformer<T> implements Transformer<T, T> {
    //        boolean mIsNeedShowLoading = true; //当联网加载时，控制是否需要显示loading
    //
    //        public RxTransformer(boolean isNeedShowLoading) {
    //            mIsNeedShowLoading = isNeedShowLoading;
    //        }
    //
    //        @Override
    //        public Observable<T> call(Observable<T> tObservable) {
    //            return tObservable
    //                    .subscribeOn(Schedulers.io())
    //                    .doOnSubscribe(new Action0() {
    //                        @Override
    //                        public void call() {
    //                            //开始时执行，显示loadingDialog
    //                            if (mIsNeedShowLoading)
    //                                showOrHideLoading(true);
    //                        }
    //                    })
    //                    .doAfterTerminate(new Action0() {
    //                        @Override
    //                        public void call() {
    //                            //结束时执行，隐藏loadingDialog
    //                            if (mIsNeedShowLoading)
    //                                showOrHideLoading(false);
    //                        }
    //                    })
    //                    .observeOn(AndroidSchedulers.mainThread())
    //                    .unsubscribeOn(Schedulers.io());
    //        }

}
