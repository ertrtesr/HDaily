package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.model.interfaces.IDailyModel;
import com.hwj.hdaily.presenter.interfaces.IDailyPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IDailyView;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class DailyPresenter implements INetCallback<DailyInfo>, IDailyPresenter {

    private IDailyView mDailyView;

    @Inject
    IDailyModel mDailyModel;

    public DailyPresenter(IDailyView dailyView) {
        this.mDailyView = dailyView;
        initInject();
    }

    @Override
    public void initInject() {
        DaggerPresenterComponent.builder().modelModule(new ModelModule(this)).build().inject(this);
    }

    @Override
    public void getDailyInfo() {
        mDailyModel.getDailyInfo();
    }

    @Override
    public void onStart() {
        //该方法中可以显示加载进度条
        mDailyView.showLoading();
    }

    @Override
    public void onSuccess(DailyInfo dailyInfo) {
        mDailyView.showDaily(dailyInfo);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mDailyView.showError();
    }

    @Override
    public void onComplete() {
        //该方法中可以结束加载进度条
        mDailyView.finishLoading();
    }
}
