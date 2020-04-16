package com.fdb.mvpdemo.model;


import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.mvpdemo.base.ModelHeader;
import com.fdb.mvpdemo.base.ModelParameter;
import com.fdb.mvpdemo.bean.HouseCollectListBean;
import com.fdb.mvpdemo.bean.LoginBean;
import com.fdb.mvpdemo.service.AppService;

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

    public static Observable<HouseCollectListBean> getCollectList() {
//        {"pageIndex":"1","pageSize":"6","CityId":11223}

        ModelParameter modelParameter = new ModelParameter();
//        modelParameter.addParameter("pageIndex", "-1");
//        modelParameter.addParameter("pageSize", "-1");
//        modelParameter.addParameter("CityId", "11223");

        ModelHeader modelHeader = new ModelHeader();

        return RetrofitClient.getAPIService(AppService.class)
                .houseCollectList(modelHeader.getHeaders(), modelParameter.getParams());
    }

}
