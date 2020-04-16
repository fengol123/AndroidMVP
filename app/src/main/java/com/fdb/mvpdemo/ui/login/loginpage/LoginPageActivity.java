package com.fdb.mvpdemo.ui.login.loginpage;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.baselibrary.network.NetSubscriber;
import com.fdb.baselibrary.network.OldNetSubscriber;
import com.fdb.baselibrary.utils.L;
import com.fdb.baselibrary.utils.SPUtil;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.model.AppModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPageActivity extends BaseActivity<LoginPageContract.Presenter> implements LoginPageContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialize() {
        AppModel.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new OldNetSubscriber<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        SPUtil.put("token", data.Data.Token);
                        L.i("" + data.Data.Token);
                    }
                });

        AppModel.getCollectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<HouseCollectListBean>() {
                    @Override
                    public void onSuccess(HouseCollectListBean data) {
                    }
                });
    }

    @Override
    public void showError() {

    }

    @Override
    public void enterHome() {

    }
}
