package com.fdb.baselibrary.utils;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Json工具类
 */
public class JsonUtils {
    private static Gson mGson = new Gson();

    /**
     * 把对象或者list转为json字符串
     *
     * @param object 转换对象
     * @return json字符串
     */
    @Nullable
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        String s = mGson.toJson(object);
        return s;
    }

    /**
     * 把json转为对象
     *
     * @param json  json字符串
     * @param clazz 对象的类型
     * @param <T>
     * @return 对象
     */
    @Nullable
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            T bean = mGson.fromJson(json, clazz);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 把json转为list集合
     *
     * @param json  json字符串
     * @param clazz 集合的类型
     * @param <T>
     * @return list集合
     */
    @Nullable
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = mGson.fromJson(json, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}

















