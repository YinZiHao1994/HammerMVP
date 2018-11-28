package com.source.yin.hammermvp.mvp;

import com.source.yin.hammermvp.mvp.configuration.GlobalConfiguration;
import com.source.yin.hammermvp.mvp.configuration.HttpInterceptorConfiguration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpInterceptorConfiguration httpInterceptorConfiguration = GlobalConfiguration.getHttpInterceptorConfiguration();
        if (httpInterceptorConfiguration == null) {
            return chain.proceed(request);
        }
        Request customizedRequest = httpInterceptorConfiguration.beforeRequest(chain, request);
        Response response = chain.proceed(customizedRequest);
        return httpInterceptorConfiguration.onResponse(chain, response);
    }
}
