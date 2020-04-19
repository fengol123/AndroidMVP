package com.fdb.mvpdemo.ui.mine.collect;

import com.fdb.baselibrary.base.IBasePresenter;
import com.fdb.baselibrary.base.IBaseView;
import com.fdb.baselibrary.network.callback.BaseNetCallback;
import com.fdb.mvpdemo.bean.HouseCollectListBean;

/**
 * Created by xieguofeng on 2018/8/16
 */
public interface CollectContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {
        void getList(int page, int pageSize, BaseNetCallback<HouseCollectListBean> netCallback);
    }
}
