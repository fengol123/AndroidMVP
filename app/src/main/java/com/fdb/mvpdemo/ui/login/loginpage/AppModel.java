package com.fdb.mvpdemo.ui.login.loginpage;


import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.mvpdemo.base.ModelHeader;
import com.fdb.mvpdemo.base.ModelParameter;
import com.fdb.mvpdemo.bean.LoginBean;

import rx.Observable;

/**
 * Desc
 * Author feng
 * Date   2019/4/25.
 */
public class AppModel {
    public static Observable<LoginBean> login() {
        ModelParameter modelParameter = new ModelParameter();
        modelParameter.addParameter("Mobile", "18680193200");
        modelParameter.addParameter("Password", "12345678");

        ModelHeader modelHeader = new ModelHeader();
        //        modelHeader.addHeader("","");

        return RetrofitClient.getAPIService(AppService.class)
                .login(modelHeader.getHeaders(), modelParameter.getParams());
    }

}
