package com.fdb.mvpdemo.ui.mine.housedetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.baselibrary.utils.ViewUtils;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.widget.ContentLayout;
import com.fdb.mvpdemo.widget.statusview.StatusView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseDetailActivity extends BaseActivity<HouseDetailContract.Presenter> implements HouseDetailContract.View, ContentLayout.OnConetntListener<DemandDetail> {
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private String mId;
    private StatusView mStatusView;
    private ContentLayout<DemandDetail> mContentLayout;

    @Override
    protected HouseDetailContract.Presenter createPresenter() {
        return new HouseDetailPresenter();
    }

    @Override
    protected void initialize() {
        mContentLayout = new ContentLayout<>(getActivity());
        mContentLayout.setOnConetntListener(this);
        setContentView(mContentLayout);

        getIntentData();
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

    @Override
    public void loadData(BaseNetCallback<DemandDetail> netCallback) {
        getPresenter().getDetail("961", netCallback);
    }

    @Override
    public View getContentView() {
        View view = ViewUtils.inflate(getActivity(), R.layout.activity_house_detail);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showContent(final DemandDetail bean) {
        mTvContent.setText(bean.Data.toString());
        mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContentLayout.refreshData();
            }
        });
    }
}
