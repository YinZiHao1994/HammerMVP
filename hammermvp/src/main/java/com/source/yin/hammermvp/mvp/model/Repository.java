package com.source.yin.hammermvp.mvp.model;

import com.source.yin.hammermvp.mvp.OkHttpInterceptor;
import com.source.yin.hammermvp.mvp.configuration.GlobalConfiguration;
import com.source.yin.hammermvp.mvp.configuration.HttpInterceptorConfiguration;
import com.source.yin.hammermvp.mvp.configuration.InternetConfiguration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private Retrofit retrofit;

    public Repository() {
        init();
    }

    public void init() {
        InternetConfiguration internetConfiguration = GlobalConfiguration.getInternetConfiguration();
        HttpInterceptorConfiguration httpInterceptorConfiguration = GlobalConfiguration.getHttpInterceptorConfiguration();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(internetConfiguration.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        //如果设置了拦截器
        if (httpInterceptorConfiguration != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new OkHttpInterceptor())
                    .build();
            builder.client(client);
        }
        retrofit = builder.build();
    }

    public <T> T createRetrofitService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
