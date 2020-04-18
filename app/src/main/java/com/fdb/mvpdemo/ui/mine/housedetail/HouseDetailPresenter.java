package com.fdb.mvpdemo.ui.mine.housedetail;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.callback.CommonNetCallbackImpl;
import com.fdb.baselibrary.network.callback.OldNetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.model.AppModel;

import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class HouseDetailPresenter extends BasePresenter<HouseDetailContract.View> implements HouseDetailContract.Presenter {

    @Override
    public void getDetail(String id) {
        Subscription subscription = AppModel.demandDetail(id)
                .compose(new ThreadTransformer<DemandDetail>())
                .subscribe(new OldNetSubscriber<DemandDetail>(new CommonNetCallbackImpl<DemandDetail>(getView()) {
                    @Override
                    public void onSuccess(@NonNull DemandDetail data) {
                        L.i("onSuccess");
                        getView().showContent(data.Data.toString());
                    }
                }));
        addSubscription(subscription);
    }
}
