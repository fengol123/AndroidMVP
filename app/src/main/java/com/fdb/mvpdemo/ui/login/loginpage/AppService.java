package com.fdb.mvpdemo.ui.login.loginpage;


import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import rx.Observable;

public interface AppService {
    @POST(HostUrl.ACCOUNT_LOGIN)
    Observable<Object> login(@HeaderMap HashMap<String, String> header, @Body HashMap Body);

    //
//    /**
//     * 书城首页
//     */
//    @POST(HostUrl.App.VERSION)
//    Observable<BaseNetBean<VersionBean>> version(@HeaderMap HashMap<String, String> header, @Body HashMap Body);




}















