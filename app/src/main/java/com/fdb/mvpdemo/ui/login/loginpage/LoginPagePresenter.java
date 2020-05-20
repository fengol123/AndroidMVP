package com.fdb.mvpdemo.ui.login.loginpage;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fdb.baselibrary.base.BasePresenter;
import com.fdb.baselibrary.bean.DataErrorBean;
import com.fdb.baselibrary.network.callback.OldNetSubscriber;
import com.fdb.baselibrary.network.callback.ViewNetCallback;
import com.fdb.baselibrary.network.transformer.ThreadTransformer;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.model.AppModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class LoginPagePresenter extends BasePresenter<LoginPageContract.View> implements LoginPageContract.Presenter {

    @Override
    public void login(String userName, String psw) {
        CompositeDisposable compositeDisposable = mCompositeDisposable;

        //如果不提供view的情况,可以使用如下回调方式, 使用 EasyNetCallback
        //后台旧接口使用 OldNetSubscriber, 新接口使用 NetSubscriber
        AppModel.login(userName, psw)
                .map(new Function<LoginBean, LoginBean>() {
                    @Override
                    public LoginBean apply(LoginBean loginBean) throws Exception {
                        Thread.sleep(3000);
                        return loginBean;
                    }
                })
                .compose(new ThreadTransformer<LoginBean>())
                .subscribe(new OldNetSubscriber<>(mCompositeDisposable, new ViewNetCallback<LoginBean>(getView()) {
                    @Override
                    public void onSuccess(@NonNull LoginBean data) {
                        L.i("onSuccess");
//                        SPUtil.put("token", data.Data.Token);
//                        getView().enterHome();
                    }

                    @Override
                    public void onDataError(@NonNull DataErrorBean error) {
                        getView().showError(error.message);
                    }
                }));

        Log.d("feng", "");

        //如果不提供view的情况,可以使用如下回调方式, 使用 EasyNetCallback
        //        Subscription subscription = AppModel.login(userName, psw)
        //                .compose(new ThreadTransformer<LoginBean>())
        //                .subscribe(new OldNetSubscriber<LoginBean>(new EasyNetCallback<LoginBean>() {
        //                    @Override
        //                    public void onSuccess(@NonNull LoginBean data) {
        //                        L.i("onSuccess");
        //                        getView().enterHome();
        //                    }
        //
        //                    @Override
        //                    public void onPrepare(Subscription subscription) {
        //                        //显示loading
        //                    }
        //
        //                    @Override
        //                    public void onFinish() {
        //                        //隐藏loading
        //                    }
        //                }));
        //        addSubscription(subscription);
    }
}
