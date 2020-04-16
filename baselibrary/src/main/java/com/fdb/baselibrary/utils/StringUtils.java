package com.fdb.baselibrary.utils;

import android.text.TextUtils;

/**
 * Created by xieguofeng on 2018/12/20
 * 字符串处理工具类
 */
public class StringUtils {
    /**
     * 对字符串判空
     *
     * @param s
     * @return null, 空格, 空字符串返回true ; 否则:false
     */
    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        if (TextUtils.isEmpty(s.trim())) {
            return true;
        }

        return false;
    }

}
