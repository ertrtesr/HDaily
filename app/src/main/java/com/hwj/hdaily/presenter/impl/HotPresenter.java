package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.model.interfaces.IHotModel;
import com.hwj.hdaily.presenter.interfaces.IHotPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IHotView;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class HotPresenter implements INetCallback<HotInfo>, IHotPresenter {

    private IHotView mHotView;

    @Inject
    IHotModel mHotModel;

    public HotPresenter(IHotView hotView) {
        mHotView = hotView;
        initInject();
    }

    @Override
    public void initInject() {
        DaggerPresenterComponent.builder()
                .modelModule(new ModelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void getHotInfo() {
        mHotModel.getHotInfo();
    }

    @Override
    public void onStart() {
        mHotView.showLoading();
    }

    @Override
    public void onSuccess(HotInfo hotInfo) {
        mHotView.showHot(hotInfo);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mHotView.showError();
    }

    @Override
    public void onComplete() {
        mHotView.finishLoading();
    }
}
