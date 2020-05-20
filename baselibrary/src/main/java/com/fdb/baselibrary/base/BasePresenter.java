package com.fdb.baselibrary.base;


import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter基类 已实现以下功能
 * <pre>
 *     1.mvp架构
 *     2.加载网络方法封装
 * </pre>
 */
public class BasePresenter<T> implements IBasePresenter<T> {
    private T mView; //mvp的view
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(T display) {
        this.mView = display;
    }

    @Override
    public void detachView() {
        dispose();
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

    //RxJava取消注册，以避免内存泄露
    private void dispose() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() != 0) {
            mCompositeDisposable.dispose();
        }
    }
}
