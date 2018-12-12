package com.source.yin.hammermvp.mvp.configuration;

/**
 * 全局配置信息类，在项目的{@link android.app.Application}中初始化
 */
public class GlobalConfiguration {

    private static InternetConfiguration internetConfiguration;
    private static HttpInterceptorConfiguration httpInterceptorConfiguration;

    private GlobalConfiguration(HammerConfigurationBuilder hammerConfigurationBuilder) {
        internetConfiguration = hammerConfigurationBuilder.internetConfiguration;
        httpInterceptorConfiguration = hammerConfigurationBuilder.httpInterceptorConfiguration;
    }

    public static class HammerConfigurationBuilder {
        private InternetConfiguration internetConfiguration;
        private HttpInterceptorConfiguration httpInterceptorConfiguration;

        public HammerConfigurationBuilder internetConfiguration(InternetConfiguration internetConfiguration) {
            this.internetConfiguration = internetConfiguration;
            return this;
        }

        public HammerConfigurationBuilder httpInterceptorConfiguration(HttpInterceptorConfiguration httpInterceptorConfiguration) {
            this.httpInterceptorConfiguration = httpInterceptorConfiguration;
            return this;
        }


        public GlobalConfiguration build() {
            return new GlobalConfiguration(this);
        }

    }

    public static InternetConfiguration getInternetConfiguration() {
        return internetConfiguration;
    }

    public static HttpInterceptorConfiguration getHttpInterceptorConfiguration() {
        return httpInterceptorConfiguration;
    }
}
