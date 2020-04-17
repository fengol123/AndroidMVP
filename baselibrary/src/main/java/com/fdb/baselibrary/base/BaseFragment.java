package com.fdb.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdb.baselibrary.utils.ToastUtil;

import rx.Subscription;


public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View mView = inflater.inflate(getLayoutId(), container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initialize();
    }

    @Override
    public void showLoading(Subscription subscription) {
        if (getActivity() != null) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).showLoading(null);
            }
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() != null) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).hideLoading();
            }
        }
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.s(msg);
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
