package com.fdb.baselibrary.base;


/**
 * Presenter基类 已实现以下功能
 * <pre>
 *     1.mvp架构
 *     2.加载网络方法封装
 * </pre>
 */
public class BasePresenter<T extends IBaseDisplay> implements IBasePresenter<T> {
    private T mView; //mvp的view

    @Override
    public void attachView(T display) {
        this.mView = display;
    }

    @Override
    public void detachView() {
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
}
