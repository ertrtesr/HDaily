package com.hwj.hdaily.di.component;

import com.hwj.hdaily.api.APIService;
import com.hwj.hdaily.di.annotation.ServiceForBaseUrl;
import com.hwj.hdaily.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/5
 * <p>
 * 描述:
 */

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    APIService getService();

    @ServiceForBaseUrl
    APIService getServiceByUrl();
}
