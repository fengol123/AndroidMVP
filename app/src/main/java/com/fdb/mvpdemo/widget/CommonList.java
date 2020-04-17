package com.fdb.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.widget.base.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private Context mContext;
    private BaseAdapter mBaseAdapter;
    private int mTotalPage;
    private int mPageSize = 10;

    public CommonList(@NonNull Context context) {
        this(context, null);
    }

    public CommonList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_common_list, null);
        ButterKnife.bind(this, view);

        mRvContent.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        mBaseAdapter = baseAdapter;
        mBaseAdapter.setPageSize(mPageSize);
        mRvContent.setAdapter(baseAdapter);
    }

    public void setPageSize(int pageSize){
        mPageSize = pageSize;
        if(mBaseAdapter != null){
            mBaseAdapter.setPageSize(mPageSize);
        }
    }

    public void setTotalPage(int page) {
        mTotalPage = page;
    }

    public interface OnLoadDataListener{
        public void onLoadData(int page, int pageSize);
    }
}

















