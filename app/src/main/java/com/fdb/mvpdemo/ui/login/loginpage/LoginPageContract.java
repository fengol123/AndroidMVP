package com.fdb.mvpdemo.ui.login.loginpage;

import com.fdb.baselibrary.base.IBasePresenter;
import com.fdb.baselibrary.base.IBaseView;

/**
 * Created by xieguofeng on 2018/8/16
 */
public interface LoginPageContract {
    interface View extends IBaseView {
        void showError(String msg);

        void enterHome();
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String userName, String psw);
    }
}
