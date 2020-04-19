package com.fdb.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.fdb.baselibrary.base.BaseApplication;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.widget.base.BaseAdapter;
import com.fdb.mvpdemo.widget.base.MoreHolder;
import com.fdb.mvpdemo.widget.statusview.StatusView;
import com.fdb.mvpdemo.widget.statusview.StatusViewBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class CommonList<T, L> extends FrameLayout {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.status_view)
    StatusView mStatusView;
    private Context mContext = BaseApplication.getApp();
    private BaseAdapter<L> mBaseAdapter;
    private int mTotalPage;
    private int mPageSize = 10;
    private int mPage = 1;

    public CommonList(@NonNull Context context) {
        this(context, null);
    }

    public CommonList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(BaseAdapter<L> baseAdapter, RecyclerView.LayoutManager layoutManager, int pageSize) {
        if (mOnListListener == null) {
            return;
        }

        init();
        mBaseAdapter = baseAdapter;
        mBaseAdapter.setPageSize(mPageSize);
        mRvContent.setLayoutManager(layoutManager);
        mRvContent.setAdapter(baseAdapter);

        mBaseAdapter.setOnLoadMoreListener(new MoreHolder.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        mPageSize = pageSize;
        mBaseAdapter.setPageSize(pageSize);

        loadData();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_common_list, this);
        ButterKnife.bind(this, view);

        //状态页配置
        mStatusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mStatusView.showLoadingView();
                        loadData();
                    }
                }).build());

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void loadData() {
        mPage = 1;
        if (mOnListListener != null) {
            mOnListListener.loadData(mPage, mPageSize, mNetCallback);
        }
    }

    private void loadMoreData() {
        mPage++;
        if (mOnListListener != null) {
            mOnListListener.loadData(mPage, mPageSize, mNetCallback);
        }
    }

    /**
     * 网络监听者
     */
    BaseNetCallback<T> mNetCallback = new BaseNetCallback<T>() {
        @Override
        public void onPrepare(Subscription subscription) {
            if (mPage == 1) {
                if (!mSrlRefresh.isRefreshing()) {
                    mStatusView.showLoadingView();
                }
            }
        }

        @Override
        public void onNetError() {
            if (mPage == 1) {
                if (mSrlRefresh.isRefreshing()) {
                    mSrlRefresh.setRefreshing(false);
                }
                mStatusView.showNetErrorView();
            } else {
                mBaseAdapter.loadMoreError();
                mPage--;
            }
        }

        @Override
        public void onDataError(@NonNull DataErrorBean error) {
            if (mPage == 1) {
                if (mSrlRefresh.isRefreshing()) {
                    mSrlRefresh.setRefreshing(false);
                }
                mStatusView.showErrorView();
            } else {
                mBaseAdapter.loadMoreError();
                mPage--;
            }
        }

        @Override
        public void onSuccess(@NonNull T data) {
            if (mOnListListener == null) {
                return;
            }
            mTotalPage = mOnListListener.getTotalPage(data);
            if (mPage == 1) {
                if (mSrlRefresh.isRefreshing()) {
                    mSrlRefresh.setRefreshing(false);
                }
                mStatusView.showContentView();
                mBaseAdapter.setData(mOnListListener.getList(data));
            } else {
                mBaseAdapter.addData(mOnListListener.getList(data));
            }

        }
    };

    private OnListListener<T, L> mOnListListener;

    public void setOnListListener(OnListListener<T, L> onListListener) {
        mOnListListener = onListListener;
    }

    public interface OnListListener<T, L> {
        public void loadData(int page, int pageSize, BaseNetCallback<T> netCallback);

        public int getTotalPage(T data);

        public List<L> getList(T data);
    }
}

















