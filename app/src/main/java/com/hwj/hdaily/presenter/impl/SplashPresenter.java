package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.model.entity.SplashInfo;
import com.hwj.hdaily.model.entity.base.HttpResult;
import com.hwj.hdaily.model.interfaces.ISplashModel;
import com.hwj.hdaily.presenter.base.BasePresenter;
import com.hwj.hdaily.presenter.interfaces.ISplashPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.ISplashView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/9
 */

public class SplashPresenter extends BasePresenter<ISplashModel, ISplashView> implements ISplashPresenter {

    public SplashPresenter(ISplashView view) {
        super(view);
    }

    @Override
    public void initInject() {
        //如果有module类就必须注入,不能忘
        getPresenterComponent().inject(this);
    }


    @Override
    public void getSplashInfo() {
        mModel.getSplashInfo();
    }

    @Override
    public void onStart() {
        //该方法中可以显示加载进度条
        mView.showLoading();
    }

    @Override
    public void onSuccess(HttpResult result) {
        if (result instanceof SplashInfo)
            mView.showSplash((SplashInfo) result);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mView.showError();
    }

    @Override
    public void onComplete() {
        //该方法中可以结束加载进度条
        mView.finishLoading();
    }
}
