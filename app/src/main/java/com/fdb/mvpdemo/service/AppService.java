package com.fdb.mvpdemo.service;


import com.fdb.mvpdemo.bean.DemandDetail;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.constant.HostUrl;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import rx.Observable;

public interface AppService {
    @POST(HostUrl.ACCOUNT_LOGIN)
    Observable<LoginBean> login(@HeaderMap HashMap<String, String> header, @Body HashMap Body);

    @POST(HostUrl.COLLECT_LIST)
    Observable<HouseCollectListBean> houseCollectList(@HeaderMap HashMap<String, String> header, @Body HashMap Body);

    @POST(HostUrl.DEMAND_DETAIL)
    Observable<DemandDetail> demandDetail(@HeaderMap HashMap<String, String> header, @Body HashMap Body);
}















