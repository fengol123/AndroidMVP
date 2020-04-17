package com.fdb.mvpdemo.ui.mine.collect;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.callback.NetSubscriber;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    @Override
    public void getList(int page, int pageSize) {
        AppModel.getCollectList(page, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<HouseCollectListBean>() {
                    @Override
                    public void onSuccess(HouseCollectListBean data) {
                        getView().showList(data.data);
                    }
                });
    }
}
