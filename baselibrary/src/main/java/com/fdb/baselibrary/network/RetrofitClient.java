package com.fdb.baselibrary.network;


import com.fdb.baselibrary.base.BaseApplication;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 配置类 主要功能如下:
 * <pre>
 *     1.超时重连等时间配置
 *     2.拦截器配置
 *     3.单例模式
 *     4.主host路径配置
 * </pre>
 */
public class RetrofitClient {
    public volatile static Retrofit retrofit = null;
    public static String mHostUrl;

    public static Retrofit getInstance() {
        if (retrofit == null)
            synchronized (RetrofitClient.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //设置超时
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
//                .addInterceptor(new ParameterInterceptor())// 拦截器
                .addInterceptor(new ChuckInterceptor(BaseApplication.getApp())); //chuck在通知栏显示请求记录

        return builder.build();
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(mHostUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    public static <T> T getAPIService(Class<T> service) {
        return getInstance().create(service);
    }

    public static void setmHostUrl(String hostUrl) {
        mHostUrl = hostUrl;
    }
}
