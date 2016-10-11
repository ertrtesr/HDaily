package com.hwj.hdaily.di.module;

import com.hwj.hdaily.api.APIService;
import com.hwj.hdaily.di.annotation.ServiceForBaseUrl;
import com.hwj.hdaily.manager.RetrofitManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/5
 * <p>
 * 描述: 提供APIService的实体
 */

@Module
public class RetrofitModule {

    private String mBaseUrl;

    public RetrofitModule() {
    }

    public RetrofitModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Singleton
    @Provides
    APIService provideAPIService() {
        return new RetrofitManager().createService(APIService.class);
    }

    @Singleton
    @Provides
    @ServiceForBaseUrl
    APIService provideAPIServiceByUrl() {
        return new RetrofitManager(mBaseUrl).createService(APIService.class);
    }
}
