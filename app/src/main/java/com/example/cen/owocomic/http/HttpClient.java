package com.example.cen.owocomic.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    // 网络客户端配置
    private static INetConfiger mConfig;

    //新建一个Retrofit对象
    private static Retrofit retrofit;

    private HttpClient(){
        retrofit =new Retrofit.Builder()
                .baseUrl(HttpUrl.baseUrl)//要访问的网络地址域名，如http://www.zhihu.com
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static class HttpClientHodler{
        private static HttpClient mInstance = new HttpClient();
    }

    /**
     * 获取 HttpClient 单例
     * @return
     */
    public static HttpClient instance(){
        return HttpClientHodler.mInstance;
    }


    /**
     * 注册配置
     *
     * @param config
     */
    public void registerConfig(INetConfiger config) {
        instance().mConfig = config;
        //赋值为空
        HttpClientHodler.mInstance = null;
    }

    /**
     * 获取对应的ApiService
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> service){
        return retrofit.create(service);
    }
}
