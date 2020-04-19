package com.lnr.android.base.framework.data.asyn.http.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class RetrofitDefaultFactory {

    public static boolean openDebug = false;

    private static OkHttpClient defaultOkHttpClient;

    public static Retrofit create(String url, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client).build();
    }

    public static Retrofit defaultRetrofit(String url) {
        if (defaultOkHttpClient == null) {
            defaultOkHttpClient = createOkHttpClient(null, null);
        }
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(defaultOkHttpClient).build();
    }


    public static OkHttpClient createOkHttpClient(SSLProvider.SSLParams sslParams, Interceptor
            netInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (sslParams != null) {
            builder.hostnameVerifier(sslParams.unSafeHostnameVerifier)
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }
        if (netInterceptor != null) {
            builder.addNetworkInterceptor(netInterceptor);
        }

        if (openDebug) {
            builder.addInterceptor(new LoggerInterceptor());
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
