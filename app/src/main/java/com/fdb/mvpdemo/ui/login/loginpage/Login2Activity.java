package com.fdb.mvpdemo.ui.login.loginpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fdb.baselibrary.network.NetSubscriber;
import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.baselibrary.utils.L;
import com.fdb.mvpdemo.bean.LoginBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetrofitClient.setmHostUrl(HostUrl.HOST_URL);
        AppModel.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetSubscriber<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean data) {
                        L.i("");
                    }
                });
    }
}
