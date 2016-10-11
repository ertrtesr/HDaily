package com.hwj.hdaily.base;

import android.os.Handler;
import android.os.Message;

import com.hwj.hdaily.base.interfaces.IHandler;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class UIHandler extends Handler {
    private IHandler handler;   //回调接口，消息传递给注册者

    public UIHandler(){

    }

    public void setHandler(IHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (handler != null) {
            handler.handleMessage(msg);     //有消息，就传递
        }
    }
}

