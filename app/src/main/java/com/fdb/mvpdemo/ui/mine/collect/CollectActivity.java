package com.fdb.mvpdemo.ui.mine.collect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.baselibrary.network.callback.NetCallback;
import com.fdb.mvpdemo.bean.HouseBean;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.ui.mine.housedetail.HouseDetailActivity;
import com.fdb.mvpdemo.widget.CommonList;
import com.fdb.mvpdemo.widget.base.BaseAdapter;
import com.fdb.mvpdemo.widget.base.BaseHolder;
import com.fdb.mvpdemo.widget.holder.HouseCollectHolder;

import java.util.List;

public class CollectActivity extends BaseActivity<CollectContract.Presenter> implements CollectContract.View, CommonList.OnListListener<HouseCollectListBean, HouseBean> {
    private BaseAdapter<HouseBean> mBaseAdapter;
    private CommonList<HouseCollectListBean, HouseBean> mCommonList;

    @Override
    protected CollectContract.Presenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected void initialize() {
        mCommonList = new CommonList<>(getActivity());
        mCommonList.setOnListListener(this);
        setContentView(mCommonList);

        mBaseAdapter = new BaseAdapter<HouseBean>() {
            @Override
            public BaseHolder<HouseBean> getHolder(FrameLayout frameLayout, int viewType) {
                return new HouseCollectHolder(frameLayout);
            }
        };
        mBaseAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener<HouseBean>() {
            @Override
            public void onItemClick(HouseBean data, int position) {
                Intent intent = HouseDetailActivity.getIntent(getActivity(), data.id + "");
                startActivity(intent);
            }
        });

        mCommonList.setAdapter(mBaseAdapter, new LinearLayoutManager(getActivity()),10);
    }

    @Override
    public void loadData(int page, int pageSize, NetCallback<HouseCollectListBean> netCallback) {
        getPresenter().getList(page, pageSize, netCallback);
    }

    @Override
    public int getTotalPage(HouseCollectListBean data) {
        return data.pageCount;
    }

    @Override
    public List<HouseBean> getList(HouseCollectListBean data) {
        return data.data;
    }
}
