package com.fdb.baselibrary.network;

import android.text.TextUtils;

import com.fdb.baselibrary.ConfigModule;
import com.fdb.baselibrary.base.BaseApplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自动加入请求网络的公共数据的Map集合, 已实现功能:
 * 1.加入公共参数, 如 token等
 * 2.对数据进行签名
 */
public class RequestParameter {
    private LinkedHashMap<String, String> mParams;

    public RequestParameter() {
        mParams = new LinkedHashMap<>();
        addCommonParams();
    }

    /**
     * 加入请求网络的公共数据
     */
    private void addCommonParams() {
        ConfigModule configModule = BaseApplication.getConfigModule();
        HashMap<String, String> commonParameters = configModule.getCommonParameters();
        if (commonParameters == null) {
            return;
        }

        Set<Map.Entry<String, String>> entries = commonParameters.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (TextUtils.isEmpty(entry.getValue())) {
                continue;
            }
            mParams.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 添加自定义的参数
     */
    public RequestParameter addParameter(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    /**
     * 对请求的数据进行签名
     */
    private LinkedHashMap<String, String> sign() {
        ConfigModule configModule = BaseApplication.getConfigModule();
        LinkedHashMap<String, String> signMap = configModule.sign(mParams);
        if (signMap == null) {
            return mParams;
        }
        return signMap;
    }

    /**
     * 获取请求的参数签名后的map集合
     *
     * @return
     */
    public HashMap<String, String> getSignParams() {
        return sign();
    }
}
