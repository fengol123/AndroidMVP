package com.fdb.mvpdemo.ui.login.loginpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fdb.baselibrary.network.DataErrorBean;
import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.baselibrary.network.RxSubscriber;

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
                .subscribe(new RxSubscriber<Object>() {
                    @Override
                    public void onNetError() {

                    }

                    @Override
                    public void onDataError(DataErrorBean error) {

                    }

                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
