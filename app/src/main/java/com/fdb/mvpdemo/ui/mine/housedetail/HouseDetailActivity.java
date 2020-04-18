package com.fdb.mvpdemo.ui.mine.housedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class HouseDetailActivity extends BaseActivity<HouseDetailContract.Presenter> implements HouseDetailContract.View {
    @BindView(R.id.tv_content)
    TextView mTvContent;
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

        mStatusView = StatusView.init(this, R.id.tv_content);
        mStatusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mStatusView.showLoadingView();
                        loadData();
                    }
                }).build());
        mStatusView.showLoadingView();

        getIntentData();
        loadData();
    }

    private void loadData(){
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
        public void onNetError() {
            mStatusView.showNetErrorView();
        }

        @Override
        public void onDataError(@NonNull DataErrorBean error) {
            mStatusView.showErrorView();
        }

        @Override
        public void onSuccess(@NonNull DemandDetail data) {
            mStatusView.showContentView();
            mTvContent.setText(data.Data.toString());
        }
    };

}
