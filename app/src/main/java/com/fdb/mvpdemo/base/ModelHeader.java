package com.fdb.mvpdemo.base;

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
