package com.fdb.baselibrary;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 公共配置的接口
 */
public interface ConfigModule {
    /**
     * 网络请求公共头部
     *
     * @return
     */
    HashMap<String, String> getCommomHeaders();

    /**
     * 网络请求公共参数
     *
     * @return
     */
    HashMap<String, String> getCommonParameters();

    /**
     * 对请求数据进行签名
     */
    LinkedHashMap<String, String> sign(LinkedHashMap<String, String> params);
}
