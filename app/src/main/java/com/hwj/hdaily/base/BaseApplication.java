package com.hwj.hdaily.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hwj.hdaily.di.component.AppComponent;
import com.hwj.hdaily.di.component.DaggerAppComponent;
import com.hwj.hdaily.di.module.AppModule;
import com.hwj.hdaily.manager.ActivityCollection;

import java.util.Iterator;
import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/8
 * <p>
 * 描述:
 */
public class BaseApplication extends Application {

    private static Context mAppContext;        //全局的应用
    public static int mMainThreadId;           //主线程id
    public static Thread mMainThread;          //主线程
    private static int mPid;                   //进程id

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        mMainThreadId = android.os.Process.myTid();         //onCreate中创建的时候myTid是主线程id
        mMainThread = Thread.currentThread();

        initCrashHandler();
        initEaseMob();
    }

    private void initEaseMob() {
        mPid = android.os.Process.myPid();
        String processAppName = getAppName(mPid);

        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e(getClass().getSimpleName(), "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    private void initCrashHandler() {
        CrashHandler.init(new CrashHandler(this));
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
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
