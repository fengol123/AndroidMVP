package com.fdb.mvpdemo.base;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 自动加入请求网络的公共数据的Map集合
 */
public class ModelParameter {
    private LinkedHashMap<String, String> mParams = new LinkedHashMap<>();

    public ModelParameter() {
        addCommonParams();
    }

    /**
     * 加入请求网络的公共数据
     */
    private void addCommonParams() {
        addParameter("CityId", "11223");
    }

    /**
     * 添加自定义的参数
     */
    public ModelParameter addParameter(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    /**
     * 获取请求的参数签名后的map集合
     *
     * @return
     */
    public HashMap<String, String> getParams() {
        return mParams;
    }
}















