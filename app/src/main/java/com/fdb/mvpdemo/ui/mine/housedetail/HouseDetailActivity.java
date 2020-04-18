package com.fdb.mvpdemo.ui.mine.housedetail;

import android.content.Context;
import android.content.Intent;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.mvpdemo.R;

public class HouseDetailActivity extends BaseActivity<HouseDetailContract.Presenter> implements HouseDetailContract.View {

    private String mId;

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
        getIntentData();
        getPresenter().getDetail("961");
    }

    private void getIntentData(){
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
    }

    public static Intent getIntent(Context context, String id) {
        Intent intent = new Intent(context, HouseDetailActivity.class);
        intent.putExtra("id", id);
        return intent;
    }
}
