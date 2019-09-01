package com.kidd.projectbase.network;


import com.kidd.projectbase.BuildConfig;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.utils.Define;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit sInstance;
    private static Apis sApiInterface;

    private static Retrofit getClient() {
        if (null == sInstance) {
            OkHttpClient client = createHttpClient();

            sInstance = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return sInstance;
    }

    private static OkHttpClient createHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder
                .addInterceptor(loggingInterceptor)
                .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static Apis getApiInterface() {
        if (null == sApiInterface) {
            sApiInterface = getClient().create(Apis.class);
        }
        return sApiInterface;
    }
}
