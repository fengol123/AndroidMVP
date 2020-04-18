package com.fdb.mvpdemo.ui.mine.housedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.widget.statusview.StatusView;
import com.fdb.mvpdemo.widget.statusview.StatusViewBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class HouseDetailActivity extends BaseActivity<HouseDetailContract.Presenter> implements HouseDetailContract.View {
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    private String mId;
    private StatusView mStatusView;

    @Override
    protected HouseDetailContract.Presenter createPresenter() {
        return new HouseDetailPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_house_detail;
    }

    @Override
    protected void initialize() {
        ButterKnife.bind(this);

        mStatusView = StatusView.init(this, R.id.srl_refresh);
        mStatusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new View.OnClickListener() {
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

        getIntentData();
        loadData();

        mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSrlRefresh.setRefreshing(true);
                loadData();
            }
        });
    }

    private void loadData() {
        getPresenter().getDetail("961", mNetCallback);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
    }

    public static Intent getIntent(Context context, String id) {
        Intent intent = new Intent(context, HouseDetailActivity.class);
        intent.putExtra("id", id);
        return intent;
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
            mTvContent.setText(data.Data.toString());
        }
    };
}
