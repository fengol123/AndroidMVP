package com.fdb.mvpdemo.ui.mine.collect;

import android.widget.TextView;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.HouseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity<CollectContract.Presenter> implements CollectContract.View {
    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected CollectContract.Presenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initialize() {
        ButterKnife.bind(this);
        getPresenter().getList(1,10);
    }

    @Override
    public void showList(List<HouseBean> list) {
        mTvContent.setText(list.toString());
    }

}
