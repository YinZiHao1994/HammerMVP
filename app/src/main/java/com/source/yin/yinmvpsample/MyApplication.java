package com.source.yin.yinmvpsample;

import android.app.Application;

import com.source.yin.hammermvp.mvp.configuration.GlobalConfiguration;
import com.source.yin.hammermvp.mvp.configuration.HttpInterceptorConfiguration;
import com.source.yin.hammermvp.mvp.configuration.InternetConfiguration;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init("hammer");

        new GlobalConfiguration.HammerConfigurationBuilder()
                .internetConfiguration(new InternetConfiguration("https://api.github.com/"))
                .httpInterceptorConfiguration(new HttpInterceptorConfiguration() {
                    @Override
                    public Request beforeRequest(Interceptor.Chain chain, Request request) {
                        RequestBody requestBody = request.body();
                        Buffer buffer = new Buffer();
                        try {
                            if (requestBody != null) {
                                requestBody.writeTo(buffer);
                                Charset charset = Charset.forName("utf-8");
                                MediaType contentType = requestBody.contentType();
                                if (contentType != null) {
                                    if (contentType.toString().contains("multipart/form-data")) {
                                        //文件类型上传的请求不打印具体内容
                                        LogUtil.logD("request = " + request);
                                        return request;
                                    }
                                }
                                LogUtil.logD("request.requestBody = " + request + "\nrequest.body = " + buffer.readUtf8());
                            } else {
                                LogUtil.logD("request = " + request);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return request;
                    }

                    @Override
                    public Response onResponse(Interceptor.Chain chain, Response response) {
                        ResponseBody responseBody = response.body();
                        try {
                            if (responseBody != null) {
                                MediaType mediaType = responseBody.contentType();
                                if (mediaType != null) {
                                    String type = mediaType.type();
                                    if (type.equals("image")) {//避免下载文件也转成字符
                                        return response;
                                    }
                                } else {
                                    // mediaType 为空的时候也不解析（可能是图片）
                                    return response;
                                }

                                if (responseBody.contentLength() < 1024 * 1024 / 10) {//避免下载文件也转成字符
                                    BufferedSource source = responseBody.source();
                                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                                    Buffer buffer = source.buffer();

                                    //获取content的压缩类型
                                    String encoding = response
                                            .headers()
                                            .get("Content-Encoding");

                                    Buffer clone = buffer.clone();

                                    Charset charset = Charset.forName("UTF-8");


                                    LogUtil.logD("httpResult = " + clone.readString(charset));

                                    //解析response content
//                                    bodyString = parseContent(responseBody, encoding, clone);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return super.onResponse(chain, response);
                    }
                })
                .build();
    }
//
//    private String parseContent(ResponseBody responseBody, String encoding, Buffer clone) {
//        Charset charset = Charset.forName("UTF-8");
//        MediaType contentType = responseBody.contentType();
//        if (contentType != null) {
//            Charset charsetResult = contentType.charset(charset);
//            if (charsetResult != null) {
//                charset = charsetResult;
//            }
//        }
//        if (encoding != null && encoding.equalsIgnoreCase("gzip")) {//content使用gzip压缩
//            return ZipHelper.decompressForGzip(clone.readByteArray(), RequestInterceptor.convertCharset(charset));//解压
//        } else if (encoding != null && encoding.equalsIgnoreCase("zlib")) {//content使用zlib压缩
//            return ZipHelper.decompressToStringForZlib(clone.readByteArray(), RequestInterceptor.convertCharset(charset));//解压
//        } else {//content没有被压缩
//            return clone.readString(charset);
//        }
//    }
}
