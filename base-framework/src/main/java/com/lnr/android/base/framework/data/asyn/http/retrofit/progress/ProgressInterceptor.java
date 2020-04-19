package com.lnr.android.base.framework.data.asyn.http.retrofit.progress;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author:lnr
 * date:2018/12/26
 */
public class ProgressInterceptor implements Interceptor {

    static final Map<Object, ProgressListener> LISTENER_MAP = new HashMap<>();

    public static void addListener(Object url, ProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }

    public static void removeListener(Object url) {
        LISTENER_MAP.remove(url);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String url = request.url().toString();
        ResponseBody body = response.body();
        return response.newBuilder().body(new ProgressResponseBody(url, body)).build();
    }
}
