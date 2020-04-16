package com.fdb.mvpdemo.ui.mine.collect;

import com.fdb.baselibrary.base.IBasePresenter;
import com.fdb.baselibrary.base.IBaseView;

import java.util.List;

/**
 * Created by xieguofeng on 2018/8/16
 */
public interface CollectContract {
    interface View extends IBaseView {
        void showList(List<> list);
    }

    interface Presenter extends IBasePresenter<View> {
        void getList(int page, int pageSize);
    }
}
