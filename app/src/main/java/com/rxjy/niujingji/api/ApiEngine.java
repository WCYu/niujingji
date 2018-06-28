package com.rxjy.niujingji.api;


import com.rxjy.niujingji.commons.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ApiEngine {

    private static final String RS_API_HOST = "https://api.dcwzg.com:9191/";
    private static final String RX_API_HOST = "http://wpsnew.rxjy.com:9090/";
    private static final String BD_API_HOST = "http://api.map.baidu.com/";
//    private static final String SW_API_HOST = "http://swb.api.cs/";
    public static final String SW_API_HOST = "https://api.niujingji.cn:8183/";
    public static final String New_SW_API_HOST = "https://api.niujingji.cn:8183/";
    public static final String INFORMATION=RS_API_HOST+"actionapi/AN_Home/ShowMyInfo";
    private Retrofit rxRetrofit;
    private Retrofit rsRetrofit;
    private Retrofit swRetrofit;
    private Retrofit bdRetrofit;

    //这里更好的保证单例的线程安全
    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //设置查看类别
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置缓存位置与缓存的大小
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "okHttpCache");
        Cache cache = new Cache(cacheFile, size);

        //添加日志拦截器，并且添加网络缓存
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetWorkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        rxRetrofit = new Retrofit.Builder()
                .baseUrl(RX_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //初始化材料的Retrofit对象
        rsRetrofit = new Retrofit.Builder()
                .baseUrl(RS_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        swRetrofit = new Retrofit.Builder()
                .baseUrl(SW_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        bdRetrofit = new Retrofit.Builder()
                .baseUrl(BD_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    //将网络请求对象封装为单例模式
    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getRsApiService() {
        return rsRetrofit.create(ApiService.class);
    }

    public ApiService getRxApiService() {
        return rxRetrofit.create(ApiService.class);
    }

    public ApiService getSwApiService() {
        return swRetrofit.create(ApiService.class);
    }

    public ApiService getBdApiService() {
        return bdRetrofit.create(ApiService.class);
    }

}
