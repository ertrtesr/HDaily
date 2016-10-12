package com.hwj.hdaily.base;

import android.app.Application;
import android.content.Context;

import com.hwj.hdaily.di.component.AppComponent;
import com.hwj.hdaily.di.component.DaggerAppComponent;
import com.hwj.hdaily.di.module.AppModule;
import com.hwj.hdaily.manager.ActivityCollection;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/8
 * <p>
 * 描述:
 */
public class BaseApplication extends Application {

    private static Context mAppContext;        //全局的应用

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;

        initCrashHandler();
    }

    private void initCrashHandler() {
        CrashHandler.init(new CrashHandler(this));
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(mAppContext)).build();
    }

    public static void exitApp() {
        if (ActivityCollection.map != null && ActivityCollection.map.size() > 0) {
            ActivityCollection.removeAll();
        }
        android.os.Process.killProcess(android.os.Process.myPid());     //杀死进程
        System.exit(0);
    }
}
