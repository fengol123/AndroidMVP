package com.fdb.mvpdemo.ui.login.loginpage;

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

//    /**
//     * 获取加密后的数据
//     *
//     * @return
//     */
//    public HashMap<String, String> getSign() {
//        //        mParams.put("time", System.currentTimeMillis() / 1000 + "");
//        HashMap<String, String> params = new HashMap<>();
//        StringBuilder sb = new StringBuilder();
//        String str = "";
//        Set<Map.Entry<String, String>> entries = mParams.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            sb.append(entry.getKey() + "=" + entry.getValue() + "&");
//        }
//
//        if (sb.length() != 0) {
//            str = sb.substring(0, sb.length() - 1);
//            str = EncryptionUtils.rsaEncode(str);
//        }
//
//        params.put("str", str);
//        return params;
//    }
}















