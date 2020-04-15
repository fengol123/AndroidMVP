package com.fdb.baselibrary.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fdb.baselibrary.utils.ToastUtil;
import com.fdb.baselibrary.widget.LoadingDialogManager;

/**
 * Activity基类 已实现以下功能
 * <pre>
 *     1.butterknife绑定控件
 *     2.可实现rxjava生命周期绑定
 *     3.可添加toolbar
 *     4.可重新加载页面
 *     5.显示和隐藏loadingdialog
 *     6.显示错误信息
 *     7.mvp架构
 * </pre>
 */

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
        setContentView(getLayoutId());
        initialize();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.s(msg);
    }

    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public void showLoading() {
        LoadingDialogManager.showLoading(getActivity());
    }

    @Override
    public void hideLoading() {
        LoadingDialogManager.dismissLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    /**
     * 初始化presenter
     */
    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 创建Presenter 需要时重写即可
     *
     * @return
     */
    protected P createPresenter() {
        return null;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法里调用,用于自定义的初始化操作
     */
    protected abstract void initialize();

}
