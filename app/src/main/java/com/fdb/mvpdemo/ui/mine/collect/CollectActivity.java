package com.fdb.mvpdemo.ui.mine.collect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.baselibrary.base.BaseApplication;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.HouseBean;
import com.fdb.mvpdemo.widget.base.BaseAdapter;
import com.fdb.mvpdemo.widget.base.BaseHolder;
import com.fdb.mvpdemo.widget.holder.HouseCollectHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity<CollectContract.Presenter> implements CollectContract.View {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    private BaseAdapter<HouseBean> mBaseAdapter;

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

        mRvContent.setLayoutManager(new LinearLayoutManager(BaseApplication.getApp()));
        mBaseAdapter = new BaseAdapter<HouseBean>() {
            @Override
            public BaseHolder<HouseBean> getHolder(FrameLayout frameLayout, int viewType) {
                return new HouseCollectHolder(frameLayout);
            }
        };
        mRvContent.setAdapter(mBaseAdapter);

        getPresenter().getList(1, 10);
    }

    @Override
    public void showList(List<HouseBean> list) {
        mBaseAdapter.setData(list);
    }

}
