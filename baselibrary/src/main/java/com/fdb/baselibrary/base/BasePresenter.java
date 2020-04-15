package com.fdb.baselibrary.base;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter基类 已实现以下功能
 * <pre>
 *     1.mvp架构
 *     2.加载网络方法封装
 * </pre>
 */
public class BasePresenter<T> implements IBasePresenter<T> {
    private T mView; //mvp的view
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T display) {
        this.mView = display;
    }

    @Override
    public void detachView() {
        unSubscribe();
        this.mView = null;
    }

    /**
     * 获取mvp的view
     *
     * @return
     */
    protected T getView() {
        return mView;
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    //RxJava取消注册，以避免内存泄露
    private void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
