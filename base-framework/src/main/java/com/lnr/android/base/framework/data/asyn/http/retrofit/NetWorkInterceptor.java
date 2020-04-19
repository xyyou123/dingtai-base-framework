package com.lnr.android.base.framework.data.asyn.http.retrofit;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ems on 2017/3/3.
 * 网络拦截器
 */
public class NetWorkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String s = request.url().toString();
        request = filter(s, request);
        return chain.proceed(request);
    }

    private Request filter(String s, Request request) {
        return request.newBuilder()
                .addHeader("Authorization", authorization()).build();
    }

    private static String authorization() {
//        return MessageFormat.format("Token {0},{1}", Framework.currentUser.getUserId(), Framework.currentUser.getToken());
        return "";
    }
}
