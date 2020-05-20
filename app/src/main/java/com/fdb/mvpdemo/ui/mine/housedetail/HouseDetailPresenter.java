package com.fdb.mvpdemo.ui.mine.housedetail;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.callback.NetCallback;
import com.fdb.baselibrary.network.callback.OldNetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.model.AppModel;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class HouseDetailPresenter extends BasePresenter<HouseDetailContract.View> implements HouseDetailContract.Presenter {

    @Override
    public void getDetail(String id, NetCallback<DemandDetail> NetCallback) {
        AppModel.demandDetail(id)
                .compose(new ThreadTransformer<DemandDetail>())
                .subscribe(new OldNetSubscriber<DemandDetail>(mCompositeDisposable, NetCallback));
        //        addSubscription(subscription);
    }

}
