package com.fdb.mvpdemo.ui.mine.collect;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.callback.CommonNetCallbackImpl;
import com.fdb.baselibrary.network.callback.NetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    @Override
    public void getList(int page, int pageSize) {
        Subscription subscription = AppModel.getCollectList(page, pageSize)
                .map(new Func1<HouseCollectListBean, HouseCollectListBean>() {
                    @Override
                    public HouseCollectListBean call(HouseCollectListBean loginBean) {
                        L.i("sleep");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return loginBean;
                    }
                })
                .compose(new ThreadTransformer<HouseCollectListBean>())
                .subscribe(new NetSubscriber<>(new CommonNetCallbackImpl<HouseCollectListBean>(getView()){
                    @Override
                    public void onSuccess(@NonNull HouseCollectListBean data) {
                        getView().showList(data.data);
                    }
                }));
        addSubscription(subscription);

    }
}
