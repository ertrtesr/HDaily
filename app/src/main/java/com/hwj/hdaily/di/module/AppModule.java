package com.hwj.hdaily.di.module;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hwj.hdaily.base.BaseApplication;
import com.hwj.hdaily.di.annotation.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/9/30
 * <p>
 * 描述:
 */

@AppScope
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    public Thread provideMainThread() {
        return BaseApplication.mMainThread;
    }

    @Provides
    public int provideMainThreadId() {
        return BaseApplication.mMainThreadId;
    }
}
