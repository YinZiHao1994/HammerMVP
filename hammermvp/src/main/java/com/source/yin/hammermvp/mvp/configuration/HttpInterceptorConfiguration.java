package com.source.yin.hammermvp.mvp.configuration;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HttpInterceptorConfiguration {

    public Request beforeRequest(Interceptor.Chain chain, Request request) {
        return request;
    }

    public Response onResponse(Interceptor.Chain chain, Response response) {
        return response;
    }
}
