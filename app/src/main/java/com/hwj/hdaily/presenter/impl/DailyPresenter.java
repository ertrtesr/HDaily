package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.model.entity.base.HttpResult;
import com.hwj.hdaily.model.interfaces.IDailyModel;
import com.hwj.hdaily.presenter.interfaces.BasePresenter;
import com.hwj.hdaily.presenter.interfaces.IDailyPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IDailyView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class DailyPresenter extends BasePresenter<IDailyModel, IDailyView> implements IDailyPresenter {

    public DailyPresenter(IDailyView view) {
        super(view);
    }

    @Override
    public void initInject() {
        getPresentComponent().inject(this);
    }

    @Override
    public void getDailyInfo() {
        mModel.getDailyInfo();
    }

    @Override
    public void onStart() {
        //该方法中可以显示加载进度条
        mView.showLoading();
    }

    @Override
    public void onSuccess(HttpResult result) {
        if (result instanceof DailyInfo)
            mView.showDaily((DailyInfo) result);
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
