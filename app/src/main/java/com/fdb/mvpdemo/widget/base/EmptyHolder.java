package com.fdb.mvpdemo.widget.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fdb.baselibrary.base.BaseApplication;
import com.fdb.mvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xieguofeng on 2018/7/16
 */
public class EmptyHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    @BindView(R.id.ll_load_error)
    LinearLayout mLlLoadError;
    private ViewGroup mRootView;
    public static final int LOADING_STATE = 0;  //正在加载
    public static final int NO_DATA_STATE = 1; // 没有数据
    public static final int LOAD_ERROR_STATE = 2; //加载失败
    private int mCurrentState = LOADING_STATE; //当前状态

    public EmptyHolder(View itemView) {
        super(itemView);
        mRootView = (ViewGroup) itemView;
        View view = View.inflate(BaseApplication.getApp(), R.layout.view_empty, null);
        mRootView.addView(view);
        ButterKnife.bind(this, mRootView);
    }

    /**
     * 设置不同的状态的视图
     *
     * @param i
     */
    public void setState(int i) {
        mCurrentState = i;
        switch (i) {
            case LOADING_STATE:
                setLoading();
                break;
            case NO_DATA_STATE:
                setNoData();
                break;
            case LOAD_ERROR_STATE:
                setLoadError();
                break;
        }
    }

    private void setLoading() {
        //        mLlLoading.setVisibility(View.VISIBLE);
        //        mLlLoadError.setVisibility(View.GONE);
        //        mLlNoData.setVisibility(View.GONE);
    }

    private void setNoData() {
        mLlLoading.setVisibility(View.GONE);
        mLlLoadError.setVisibility(View.GONE);
        mLlNoData.setVisibility(View.VISIBLE);
    }

    private void setLoadError() {
        mLlLoading.setVisibility(View.GONE);
        mLlLoadError.setVisibility(View.VISIBLE);
        mLlNoData.setVisibility(View.GONE);
    }

}














