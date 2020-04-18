package com.fdb.mvpdemo.model;


import com.fdb.baselibrary.network.RetrofitClient;
import com.fdb.mvpdemo.base.ModelHeader;
import com.fdb.mvpdemo.base.ModelParameter;
import com.fdb.mvpdemo.bean.DemandDetail;
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
    public static Observable<LoginBean> login(String userName, String psw) {
        ModelParameter modelParameter = new ModelParameter();
        modelParameter.addParameter("Mobile", userName);
        modelParameter.addParameter("Password", psw);

        ModelHeader modelHeader = new ModelHeader();
        return RetrofitClient.getAPIService(AppService.class)
                .login(modelHeader.getHeaders(), modelParameter.getParams());
    }

    public static Observable<HouseCollectListBean> getCollectList(int page, int pageSize) {
        //        {"pageIndex":"1","pageSize":"6","CityId":11223}

        ModelParameter modelParameter = new ModelParameter();
        modelParameter.addParameter("pageIndex", page + "");
        modelParameter.addParameter("pageSize", pageSize + "");

        ModelHeader modelHeader = new ModelHeader();

        return RetrofitClient.getAPIService(AppService.class)
                .houseCollectList(modelHeader.getHeaders(), modelParameter.getParams());
    }

    public static Observable<DemandDetail> demandDetail(String id) {
        ModelParameter modelParameter = new ModelParameter();
        modelParameter.addParameter("Id", id);

        ModelHeader modelHeader = new ModelHeader();

        return RetrofitClient.getAPIService(AppService.class)
                .demandDetail(modelHeader.getHeaders(), modelParameter.getParams());
    }

}
