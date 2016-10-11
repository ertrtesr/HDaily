package com.hwj.hdaily.model.impl;

import com.hwj.hdaily.api.API;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.manager.ActivityCollection;
import com.hwj.hdaily.model.entity.SplashInfo;
import com.hwj.hdaily.model.interfaces.ISplashModel;
import com.hwj.hdaily.view.activity.SplashActivity;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/9
 */

public class SplashModel implements ISplashModel {

    private INetCallback<SplashInfo> mCallback;
    private static final String RES = "1080*1776";

    public SplashModel(INetCallback<SplashInfo> callback) {
        this.mCallback = callback;
    }

    /**
     * 获取开屏页信息
     */
    @Override
    public void getSplashInfo() {
        API.doApi(API.service().getSplashInfo(RES),
                new BaseSubscriber<SplashInfo>(mCallback),
                ActivityCollection.getActivity(SplashActivity.class));
    }
}
