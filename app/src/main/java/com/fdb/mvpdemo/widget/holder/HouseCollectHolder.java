package com.fdb.mvpdemo.widget.holder;

import android.view.View;
import android.widget.TextView;

import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.HouseBean;
import com.fdb.mvpdemo.widget.base.BaseHolder;

import butterknife.BindView;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class HouseCollectHolder extends BaseHolder<HouseBean> {
    @BindView(R.id.tv_name)
    TextView mTvName;

    public HouseCollectHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_house_collect;
    }

    @Override
    protected void onSetData() {
        mTvName.setText(mBean.communityName);
    }
}
