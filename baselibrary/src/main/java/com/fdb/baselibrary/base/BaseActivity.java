package com.fdb.baselibrary.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fdb.baselibrary.utils.ToastUtil;
import com.fdb.baselibrary.widget.CommonToolbar;
import com.fdb.baselibrary.widget.LoadingDialogManager;

import butterknife.BuildConfig;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseDisplay {
    public final String TAG = getClass().getSimpleName();
    private P mPresenter;
    protected Unbinder unbinder;
    private CommonToolbar mCommonToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContentView();
        initPresenter();
        initialize();

        //显示当前的Activity路径
        if (BuildConfig.DEBUG) {
//            L.e("当前打开的Activity:  " + getClass().getName());
        }
    }

    /**
     * 初始化布局,包括toolbar和根布局
     */
    public void initContentView() {
        mCommonToolbar = setToolbar();
        if (mCommonToolbar != null) {
            //添加toolbar
            LinearLayout view = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            view.setOrientation(LinearLayout.VERTICAL);
            view.setFitsSystemWindows(true);
            view.addView(mCommonToolbar);
            View rootView = View.inflate(this, getLayoutId(), null);
            LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            rootView.setLayoutParams(rootParams);
            view.addView(rootView);
            setContentView(view);
            setSupportActionBar(mCommonToolbar);//这里才是真的将toolbar设置为actionbar
        } else {
            setContentView(getLayoutId());
        }
        unbinder = ButterKnife.bind(this);//ButterKnife
    }

    /**
     * 重新加载Activity
     * 此方法会比 recreate() 效果更好
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        System.gc();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.s(msg);
    }

    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressDialog() {
        LoadingDialogManager.showLoading(getActivity());
    }

    @Override
    public void hideProgressDialog() {
        LoadingDialogManager.dismissLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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

    /**
     * 添加toolbar
     *
     * @return
     */
    protected CommonToolbar setToolbar() {
        return null;
    }
}
