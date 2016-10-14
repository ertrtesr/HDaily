package com.hwj.hdaily.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hwj.hdaily.listener.OnMessageReceivedListener;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.utils.NetworkUtils;
import com.hwj.hdaily.utils.UIUtils;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/14
 */

public class EMConnectionService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //注册连接监听
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {
                System.out.println("连接聊天服务器成功");
            }

            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除
                    LogUtils.i(getClass().getSimpleName(), "账号已被移除");
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                    LogUtils.i(getClass().getSimpleName(), "账号已在其他设备登录");
                } else {
                    if (NetworkUtils.isConnected(UIUtils.getContext())) {
                        LogUtils.i(getClass().getSimpleName(), "连接聊天服务器失败");
                    } else {
                        LogUtils.i(getClass().getSimpleName(), "网络异常,请重试");
                    }
                }
            }
        });

        //注册消息监听器
        EMClient.getInstance().chatManager().addMessageListener(new OnMessageReceivedListener());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
