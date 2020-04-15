package com.fdb.mvpdemo.ui.login.loginpage;


import com.fdb.baselibrary.network.RetrofitClient;

import java.util.HashMap;

import rx.Observable;

/**
 * Desc
 * Author feng
 * Date   2019/4/25.
 */
public class AppModel {
    public static Observable<Object> login() {
        ModelParameter modelParameter = new ModelParameter();
        modelParameter.addParameter("Mobile", "18680193200");
//        modelParameter.addParameter("Password", "1234");

        return RetrofitClient.getAPIService(AppService.class)
                .login(new HashMap<String, String>(), modelParameter.getParams());
    }

}
