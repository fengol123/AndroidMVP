package com.fdb.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.fdb.baselibrary.base.BaseApplication;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.widget.statusview.StatusView;
import com.fdb.mvpdemo.widget.statusview.StatusViewBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/18.
 */
public class ContentLayout extends FrameLayout {
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.status_view)
    StatusView mStatusView;
    private Context mContext = BaseApplication.getApp();

    public ContentLayout(@NonNull Context context) {
        this(context, null);
    }

    public ContentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_layout_content, this);
        ButterKnife.bind(this, view);

        //状态页配置
        mStatusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mStatusView.showLoadingView();
                        if (mOnConetntListener != null) {
                            mOnConetntListener.loadData(mNetCallback);
                        }
                    }
                }).build());

        //刷新回调
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mOnConetntListener != null) {
                    mOnConetntListener.loadData(mNetCallback);
                }
            }
        });

        //添加内容布局
        if (mOnConetntListener != null) {
            mSrlRefresh.addView(mOnConetntListener.getConetntView());
        }

        if (mOnConetntListener != null) {
            mOnConetntListener.loadData(mNetCallback);//加载数据
        }
    }

    /**
     * 网络监听者
     */
    BaseNetCallback<DemandDetail> mNetCallback = new BaseNetCallback<DemandDetail>() {
        @Override
        public void onPrepare(Subscription subscription) {
            if (!mSrlRefresh.isRefreshing()) {
                mStatusView.showLoadingView();
            }
        }

        @Override
        public void onNetError() {
            if (mSrlRefresh.isRefreshing()) {
                mSrlRefresh.setRefreshing(false);
            }
            mStatusView.showNetErrorView();
        }

        @Override
        public void onDataError(@NonNull DataErrorBean error) {
            if (mSrlRefresh.isRefreshing()) {
                mSrlRefresh.setRefreshing(false);
            }
            mStatusView.showErrorView();
        }

        @Override
        public void onSuccess(@NonNull DemandDetail data) {
            if (mSrlRefresh.isRefreshing()) {
                mSrlRefresh.setRefreshing(false);
            }
            mStatusView.showContentView();
            if (mOnConetntListener != null) {
                mOnConetntListener.showContent(data);
            }
        }
    };

    /**
     * 刷新数据
     */
    public void refreshData() {
        mSrlRefresh.setRefreshing(true);
        if (mOnConetntListener != null) {
            mOnConetntListener.loadData(mNetCallback);
        }
    }

    /**
     * 获取网络监听者
     *
     * @return
     */
    public BaseNetCallback<DemandDetail> getNetCallback() {
        return mNetCallback;
    }

    private OnConetntListener mOnConetntListener;

    public void setOnConetntListener(OnConetntListener listener) {
        mOnConetntListener = listener;
        init();
    }

    public interface OnConetntListener {
        /**
         * 加载数据
         *
         * @param netCallback
         */
        public abstract void loadData(BaseNetCallback<DemandDetail> netCallback);

        /**
         * 加载内容布局
         *
         * @return
         */
        public abstract View getConetntView();

        /**
         * 显示数据
         *
         * @param bean
         */
        public abstract void showContent(DemandDetail bean);
    }

}






















