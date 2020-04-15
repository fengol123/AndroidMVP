package com.fdb.baselibrary.network;

import android.text.TextUtils;

import com.fdb.baselibrary.BuildConfig;
import com.fdb.baselibrary.ConfigModule;
import com.fdb.baselibrary.base.BaseApplication;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * 对请求参数做处理的拦截器, 目前实现的功能:
 * <pre>
 *     1.请求和响应参数打印
 * </pre>
 */
public class ParameterInterceptor implements Interceptor {
    private static final String TAG = ParameterInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //打印请求日志
        if ("GET".equals(request.method())) {
//            L.d("请求地址RequestUrl=====", request.url().toString());
        } else { //POST
            logRequest(request);
        }

        //添加头部
        Request newRequest = addHeaders(chain);

        //开始请求数据
        Response response = chain.proceed(newRequest);

        //打印返回数据
        response = logResponse(response);

        return response;
    }

    /**
     * 添加公共的头部
     *
     * @param chain
     * @return
     */
    private Request addHeaders(Chain chain) {
        Request.Builder builder = chain.request().newBuilder();

        ConfigModule configModule = BaseApplication.getConfigModule();
        HashMap<String, String> commomHeaders = configModule.getCommomHeaders();
        if (commomHeaders != null) {
            Set<Map.Entry<String, String>> entries = commomHeaders.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return builder.build();
    }

    /**
     * 打印返回的数据
     *
     * @param response
     * @return
     * @throws IOException
     */
    private Response logResponse(Response response) throws IOException {
        if (!BuildConfig.DEBUG) {
            return response;
        }

        ResponseBody resultBody = response.body();
        String result = resultBody.string();
        try {
//            L.d("返回数据=====", " ");
//            L.json(result);
        } catch (Exception e) {
//            L.e(result);
        }

        /*** 因为调用ResponseBody.string()后即关闭，后续无法获取内容 */
        response = response.newBuilder()
                .body(ResponseBody.create(resultBody.contentType(), result))
                .build();

        return response;
    }

    /**
     * 打印请求日志
     *
     * @param request
     */
    private void logRequest(Request request) {
        if (!BuildConfig.DEBUG) {
            return;
        }

        HttpUrl oldUrl = request.url();
        JSONObject data = new JSONObject();

        try {
            if (request.body() instanceof FormBody) {
                // 当参数以 @Field @FieldMap 提交时
//                L.d(TAG, "instanceof FormBody");

                FormBody body = (FormBody) request.body();
                if (body != null)
                    for (int i = body.size() - 1; i >= 0; i--) {
                        String name = body.name(i);
                        String value = body.value(i);
                        data.put(name, value);
                    }
            } else if (request.body() instanceof MultipartBody) {
                // 当参数以 @MultipartBody 提交时
//                L.d(TAG, "instanceof MultipartBody");

            } else {// 当参数以 @Body 提交时
                String bodyString = bodyToString(request.body());
                if (!TextUtils.isEmpty(bodyString)) {
                    data = new JSONObject(bodyString);
//                    L.d("bodyToString---", bodyString);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        L.d("请求地址RequestUrl=====", oldUrl.url().toString());
//        L.d("请求参数=====", " ");
//        L.json(data.toString());

    }

    /**
     * body 中的内容
     *
     * @param request
     * @return
     */
    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
