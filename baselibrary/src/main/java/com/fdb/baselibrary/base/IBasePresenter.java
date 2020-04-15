package com.fdb.baselibrary.base;

/**
 * Presenter基类接口
 */
public interface IBasePresenter<T extends IBaseDisplay> {
    /**
     * 加入view
     * @param view
     */
    void attachView(T view);

    /**
     * 解除view
     */
    void detachView();
}
