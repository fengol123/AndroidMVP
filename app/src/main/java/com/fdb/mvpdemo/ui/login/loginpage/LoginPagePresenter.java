package com.fdb.mvpdemo.ui.login.loginpage;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.ApiException;
import com.fdb.baselibrary.network.OldNetSubscriber;
import com.fdb.baselibrary.utils.L;
import com.fdb.baselibrary.utils.SPUtil;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class LoginPagePresenter extends BasePresenter<LoginPageContract.View> implements LoginPageContract.Presenter {

    @Override
    public void login(String userName, String psw) {
        AppModel.login(userName, psw)
                .map(new Func1<LoginBean, LoginBean>() {
                    @Override
                    public LoginBean call(LoginBean loginBean) {
                        L.i("feng");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return loginBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new OldNetSubscriber<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        SPUtil.put("token", data.Data.Token);
                        getView().enterHome();
                    }

                    @Override
                    public void onDataError(ApiException error) {
                        getView().showError(error.message);
                    }
                });
    }
}
