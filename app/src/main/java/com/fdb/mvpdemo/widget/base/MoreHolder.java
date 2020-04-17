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
public class MoreHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.ll_load_all)
    LinearLayout mLlLoadAll;
    @BindView(R.id.ll_network_error)
    LinearLayout mLlNetworkError;
    private ViewGroup mRootView;
    public static final int NORMAL_STATE = 0;  //等待状态
    public static final int LOADING_STATE = 1; // 正在加载
    public static final int NETWORK_ERROR_STATE = 2; //网络错误
    public static final int HAS_LOADALL_STATE = 3; // 已加载全部
    private int mCurrentState = NORMAL_STATE;

    public MoreHolder(View itemView, int state ,OnLoadMoreListener listener) {
        super(itemView);
        mRootView = (ViewGroup) itemView;
        View view = View.inflate(BaseApplication.getApp(), R.layout.view_load_more, null);
        mRootView.addView(view);
        ButterKnife.bind(this, mRootView);

        //设置重新加载监听
        mLlNetworkError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(LOADING_STATE);
                if (mOnReloadMoreListener != null) {
                    mOnReloadMoreListener.onLoadMore();
                }
            }
        });

        setState(state);
        //设置监听
        mOnReloadMoreListener = listener;
    }

    /**
     * 设置不同的状态的视图
     *
     * @param i
     */
    public void setState(int i) {
        mCurrentState = i;
        switch (i) {
            case NORMAL_STATE:
                setNormal();
                break;
            case LOADING_STATE:
                setLoading();
                break;
            case NETWORK_ERROR_STATE:
                setNetworkError();
                break;
            case HAS_LOADALL_STATE:
                setHasLoadAll();
                break;
        }
    }

    private void setNormal() {
        mLlLoading.setVisibility(View.GONE);
        mLlLoadAll.setVisibility(View.GONE);
        mLlNetworkError.setVisibility(View.GONE);
    }


    private void setLoading() {
        mLlLoading.setVisibility(View.VISIBLE);
        mLlLoadAll.setVisibility(View.GONE);
        mLlNetworkError.setVisibility(View.GONE);
    }

    private void setNetworkError() {
        mLlLoading.setVisibility(View.GONE);
        mLlLoadAll.setVisibility(View.GONE);
        mLlNetworkError.setVisibility(View.VISIBLE);
    }

    private void setHasLoadAll() {
        mLlLoading.setVisibility(View.GONE);
        mLlLoadAll.setVisibility(View.VISIBLE);
        mLlNetworkError.setVisibility(View.GONE);
    }

    /**
     * 根据不同的状态,回调加载更多
     */
    public void loadMore() {
        if (mCurrentState != NORMAL_STATE) {
            return;
        }

        setState(LOADING_STATE);
        if (mOnReloadMoreListener != null) {
            mOnReloadMoreListener.onLoadMore();
        }
    }

    private OnLoadMoreListener mOnReloadMoreListener;

    public interface OnLoadMoreListener {
        void onLoadMore();  //加载更多回调
    }
}