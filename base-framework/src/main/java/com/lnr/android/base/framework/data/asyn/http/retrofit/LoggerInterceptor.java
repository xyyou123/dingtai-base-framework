package com.lnr.android.base.framework.data.asyn.http.retrofit;

import com.lnr.android.base.framework.uitl.logger.Logger;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoggerInterceptor implements Interceptor {

    private static final String TAG = "LoggerInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        StringBuilder str = new StringBuilder("请求体");
        str.append("\n请求地址：").append(request.url().toString());
        HttpUrl url = request.url();
        for (int i = 0, count = url.querySize(); i < count; i++) {
            str.append("\n参数：").append(url.queryParameterName(i)).append(" = ").append(url.queryParameterValue(i));
        }

        RequestBody body = request.body();
        if(body instanceof FormBody) {
            FormBody data = (FormBody) body;
            for (int i = 0; i < data.size(); i++) {
                str.append("\n参数：").append(data.encodedName(i)).append(" = ").append(data.encodedValue(i));
            }
        }

        Logger.log(TAG, str.toString());

        return chain.proceed(request);
    }
}
