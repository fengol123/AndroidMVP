package com.fdb.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.widget.base.BaseAdapter;
import com.fdb.mvpdemo.widget.base.MoreHolder;
import com.fdb.mvpdemo.widget.statusview.StatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class CommonList extends FrameLayout {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.status_view)
    StatusView mStatusView;
    private Context mContext;
    private BaseAdapter mBaseAdapter;
    private int mTotalPage;
    private int mPageSize = 10;
    private int mPage = 1;

    public CommonList(@NonNull Context context) {
        this(context, null);
    }

    public CommonList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_common_list, null);
        ButterKnife.bind(this, view);

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                if (mOnListListener != null) {
                    mOnListListener.loadData(mPage, mPageSize);
                }
            }
        });
    }

    public void setAdapter(BaseAdapter baseAdapter, RecyclerView.LayoutManager layoutManager) {
        mBaseAdapter = baseAdapter;
        mBaseAdapter.setPageSize(mPageSize);
        mRvContent.setLayoutManager(layoutManager);
        mRvContent.setAdapter(baseAdapter);

        mBaseAdapter.setOnLoadMoreListener(new MoreHolder.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                if (mOnListListener != null) {
                    mOnListListener.loadData(mPage, mPageSize);
                }
            }
        });
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
        if (mBaseAdapter != null) {
            mBaseAdapter.setPageSize(mPageSize);
        }
    }

    public void setTotalPage(int page) {
        mTotalPage = page;
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
        public void onSuccess(@NonNull DemandDetail data) {
            //            if (mSrlRefresh.isRefreshing()) {
            //                mSrlRefresh.setRefreshing(false);
            //            }
            //            mStatusView.showContentView();
            //            if (mOnConetntListener != null) {
            //                mOnConetntListener.showContent(data);
            //            }
        }
    };

    private OnListListener mOnListListener;

    public void setOnListListener(OnListListener onListListener) {
        mOnListListener = onListListener;
    }

    public interface OnListListener {
        public void loadData(int page, int pageSize);
    }
}

















