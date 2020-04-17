package com.fdb.mvpdemo.ui.login.loginpage;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.network.ApiException;
import com.fdb.baselibrary.network.CommonNetCallbackImpl;
import com.fdb.baselibrary.network.OldNetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class LoginPagePresenter extends BasePresenter<LoginPageContract.View> implements LoginPageContract.Presenter {

    @Override
    public void login(String userName, String psw) {
        Subscription subscription = AppModel.login(userName, psw)
                .map(new Func1<LoginBean, LoginBean>() {
                    @Override
                    public LoginBean call(LoginBean loginBean) {
                        L.i("sleep");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return loginBean;
                    }
                })
                .compose(new ThreadTransformer<LoginBean>())
                .subscribe(new OldNetSubscriber<LoginBean>(new CommonNetCallbackImpl<LoginBean>(getView()) {
                    @Override
                    public void onSuccess(@NonNull LoginBean data) {
                        L.i("onSuccess");
                        getView().enterHome();
                    }

                    @Override
                    public void onDataError(@NonNull ApiException error) {
                        getView().showError(error.message);
                    }
                }));
        addSubscription(subscription);
    }

    //    @Override
    //    public void onSuccess(LoginBean data) {
    //        SPUtil.put("token", data.Data.Token);
    //        getView().enterHome();
    //    }
    //
    //    @Override
    //    public void onDataError(ApiException error) {
    //        getView().showError(error.message);
    //    }
}
