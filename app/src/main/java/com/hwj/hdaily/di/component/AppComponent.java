package com.hwj.hdaily.di.component;

import android.content.Context;
import android.os.Handler;

import com.hwj.hdaily.di.annotation.AppScope;
import com.hwj.hdaily.di.module.AppModule;

import dagger.Component;

/**
 * Created by huangwenjian on 16/9/30.
 */

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

    Handler getHandler();

    Thread getMainThread();

    int getMainThreadId();
}
