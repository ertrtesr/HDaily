package com.hwj.hdaily.manager;

import android.text.TextUtils;
import android.util.Log;

import com.hwj.hdaily.api.APIService;
import com.hwj.hdaily.manager.interceptor.BaseInterceptor;
import com.hwj.hdaily.manager.interceptor.CacheInterceptor;
import com.hwj.hdaily.utils.UIUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: huangwenjian
 * -
 * 描述: Retrofit管理类
 * -
 * 日期: 16/8/22
 */
public class RetrofitManager {
    private static final int DEFAULT_TIMEOUT = 10 * 1000;
    private static final String mBaseUrl = APIService.BASE_URL;

    private Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private Cache mCache = null;
    private File mHttpCacheDirectory;

    public RetrofitManager() {
        this(mBaseUrl);
    }

    /**
     * @param baseUrl 自定义的baseUrl
     */
    public RetrofitManager(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = mBaseUrl;
        }
        if (mHttpCacheDirectory == null) {
            mHttpCacheDirectory = new File(UIUtils.getContext().getCacheDir(), "retrofit_cache");
        }
        try {
            if (mCache == null) {
                mCache = new Cache(mHttpCacheDirectory, 10 * 1024 * 1024);        //创建10MB的缓存空间
            }
        } catch (Exception e) {
            Log.e("OkHttp", "Could not create http cache", e);
        }

        Map<String, String> headers = null;
        CacheInterceptor cacheInterceptor = new CacheInterceptor(UIUtils.getContext());
        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(mCache)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(4, 10 * 1000, TimeUnit.MILLISECONDS))    // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里4个，和每个保持时间为10s
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 创建APIService
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
