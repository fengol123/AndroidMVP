package com.fdb.mvpdemo.ui.mine.collect;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.callback.NetCallback;
import com.fdb.baselibrary.network.callback.NetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    @Override
    public void getList(int page, int pageSize, NetCallback<HouseCollectListBean> netCallback) {
        Subscription subscription = AppModel.getCollectList(page, pageSize)
                .compose(new ThreadTransformer<HouseCollectListBean>())
                .subscribe(new NetSubscriber<>(netCallback));
        addSubscription(subscription);
    }
}
