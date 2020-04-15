package com.fdb.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdb.baselibrary.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Fragment基类  已实现以下功能
 * <pre>
 *     1.butterknife绑定控件
 *     2.可实现rxjava生命周期绑定
 *     3.显示和隐藏loadingdialog
 *     4.显示错误信息
 *     5.mvp架构
 * </pre>
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseDisplay {
    protected Unbinder unbinder;
    protected AppCompatActivity mActivity;
    protected Context mContext;
    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (AppCompatActivity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View mView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initialize();
    }

    @Override
    public void showProgressDialog() {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.s(msg);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    /**
     * 创建Presenter 需要时重写即可
     *
     * @return
     */
    public P createPresenter() {
        return null;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initialize();


}
