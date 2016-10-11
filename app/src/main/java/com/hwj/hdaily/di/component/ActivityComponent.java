package com.hwj.hdaily.di.component;

import com.hwj.hdaily.di.annotation.ActivityScope;
import com.hwj.hdaily.di.module.PresenterModule;
import com.hwj.hdaily.view.activity.SplashActivity;

import dagger.Component;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/6
 * <p>
 * 描述: 将Presenter实体注入到Activity中
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {PresenterModule.class})
public interface ActivityComponent {
    void inject(SplashActivity activity);
}
