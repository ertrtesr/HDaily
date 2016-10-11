package com.hwj.hdaily.di.module;

import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.di.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/8
 */

@Module
public class ActivityModule {
    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public BaseActivity provideActivity() {
        return mActivity;
    }
}
