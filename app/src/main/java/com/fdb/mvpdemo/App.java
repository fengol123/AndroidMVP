package com.fdb.mvpdemo;

import com.fdb.baselibrary.base.BaseApplication;
import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.mvpdemo.constant.HostUrl;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/15.
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.setmHostUrl(HostUrl.HOST_URL);
    }
}
