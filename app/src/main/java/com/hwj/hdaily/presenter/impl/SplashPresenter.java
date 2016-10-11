package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.SplashInfo;
import com.hwj.hdaily.model.interfaces.ISplashModel;
import com.hwj.hdaily.presenter.interfaces.ISplashPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.ISplashView;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/9
 */

public class SplashPresenter implements INetCallback<SplashInfo>, ISplashPresenter {

    @Inject
    ISplashModel mSplashModel;

    private ISplashView mSplashView;

    @Override
    public void initInject() {
        //如果有module类就必须注入,不能忘
        DaggerPresenterComponent.builder().modelModule(new ModelModule(this)).build().inject(this);
    }

    public SplashPresenter(ISplashView splashView) { //括号中的参数需要ActivityModule中的provideSplashActivity()方法提供
        mSplashView = splashView;
        initInject();       //必须要在构造方法中注入,不能忘,此处架构设计的不好,每次构造方法中都要写这句
    }

    @Override
    public void getSplashInfo() {
        mSplashModel.getSplashInfo();
    }

    @Override
    public void onStart() {
        //该方法中可以显示加载进度条
        mSplashView.showLoading();
    }

    @Override
    public void onSuccess(SplashInfo splashInfo) {
        System.out.println(splashInfo.getImg());
        mSplashView.showSplash(splashInfo);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mSplashView.showError();
    }

    @Override
    public void onComplete() {
        //该方法中可以结束加载进度条
        mSplashView.finishLoading();
    }
}
