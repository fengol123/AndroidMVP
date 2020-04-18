package com.fdb.mvpdemo.ui.mine.housedetail;

import com.fdb.baselibrary.base.IBasePresenter;
import com.fdb.baselibrary.base.IBaseView;
import com.fdb.baselibrary.network.callback.NetCallback;
import com.fdb.mvpdemo.bean.DemandDetail;

/**
 * Created by xieguofeng on 2018/8/16
 */
public interface HouseDetailContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {
        void getDetail(String id, NetCallback<DemandDetail> NetCallback);
    }
}
