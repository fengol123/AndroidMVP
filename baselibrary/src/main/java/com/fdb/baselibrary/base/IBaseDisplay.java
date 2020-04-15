package com.fdb.baselibrary.base;

import android.content.Context;

/**
 * View层的基类接口
 */
public interface IBaseDisplay {
    /**
     * 获取context
     * @return
     */
    Context getContext();

    /**
     * 显示loadingdialog
     */
    void showProgressDialog();

    /**
     * 隐藏loadingdialog
     */
    void hideProgressDialog();

    /**
     * 显示信息
     * @param msg
     */
    void showMessage(String msg);

    /**
     * 获取生命周期绑定
     * @return
     */


}
