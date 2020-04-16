package com.fdb.mvpdemo.base;

import com.fdb.baselibrary.utils.SPUtil;
import com.fdb.baselibrary.utils.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Desc
 * Author feng
 * Date   2019/4/26.
 */
public class ModelHeader {
    private LinkedHashMap<String, String> mHeaders = new LinkedHashMap<>();

    public ModelHeader() {
        addCommonHeaders();
    }

    private void addCommonHeaders() {
        String token = (String) SPUtil.get("token", "");
        if (!StringUtils.isEmpty(token)) {
            addHeader("Authorization", "Bearer " + token);
        }
        addHeader("MobileInfo", "{\"SerialNum\":\"20200416181417629\",\"DeviceType\":\"HONORCOL-AL10\",\"OS\":2,\"Version\":\"3.3.8\",\"OSVersion\":\"8.1.0\",\"IMEI\":\"865988043653831\"}");
        addHeader("location", "{\"lng\":113.289966,\"lat\":23.131701}");
    }

    public ModelHeader addHeader(String key, String value) {
        if (value != null) {
            mHeaders.put(key, value);
        }
        return this;
    }

    public HashMap<String, String> getHeaders() {
        return mHeaders;
    }
}
