package com.fdb.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

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
public abstract class ContentLayout extends FrameLayout {
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.status_view)
    StatusView mStatusView;
    private Context mContext;

    public ContentLayout(@NonNull Context context) {
        this(context, null);
    }

    public ContentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        View view = View.inflate(mContext, R.layout.view_layout_content, this);
        ButterKnife.bind(this, view);

        mStatusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mStatusView.showLoadingView();
                        loadData(mNetCallback);
                    }
                }).build());

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(mNetCallback);
            }
        });

        mSrlRefresh.addView(getConetntView());

        loadData(mNetCallback);
    }

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
            showContent(data);
        }
    };

    public abstract void loadData(BaseNetCallback<DemandDetail> netCallback);

    public abstract View getConetntView();

    public abstract void showContent(DemandDetail bean);

    public BaseNetCallback<DemandDetail> getNetCallback() {
        return mNetCallback;
    }

    //    public abstract int getContentLayout(@LayoutRes int id);
}






















