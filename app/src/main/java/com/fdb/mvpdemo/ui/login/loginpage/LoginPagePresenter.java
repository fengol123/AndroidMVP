package com.fdb.mvpdemo.ui.login.loginpage;

import android.support.annotation.NonNull;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.CommonNetCallbackImpl;
import com.fdb.baselibrary.network.callback.OldNetSubscriber;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class LoginPagePresenter extends BasePresenter<LoginPageContract.View> implements LoginPageContract.Presenter {

    @Override
    public void login(String userName, String psw) {
        Subscription subscription = AppModel.login(userName, psw)
                .compose(new ThreadTransformer<LoginBean>())
                .subscribe(new OldNetSubscriber<LoginBean>(new CommonNetCallbackImpl<LoginBean>(getView()) {
                    @Override
                    public void onSuccess(@NonNull LoginBean data) {
                        L.i("onSuccess");
                        getView().enterHome();
                    }

                    @Override
                    public void onDataError(@NonNull DataErrorBean error) {
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
