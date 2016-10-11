package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.model.entity.base.HttpResult;
import com.hwj.hdaily.model.interfaces.IHotModel;
import com.hwj.hdaily.presenter.interfaces.BasePresenter;
import com.hwj.hdaily.presenter.interfaces.IHotPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IHotView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class HotPresenter extends BasePresenter<IHotModel, IHotView> implements IHotPresenter {

    public HotPresenter(IHotView view) {
        super(view);
    }

    @Override
    public void initInject() {
        getPresentComponent().inject(this);
    }

    @Override
    public void getHotInfo() {
        mModel.getHotInfo();
    }

    @Override
    public void onStart() {
        mView.showLoading();
    }

    @Override
    public void onSuccess(HttpResult result) {
        if (result instanceof HotInfo)
            mView.showHot((HotInfo) result);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mView.showError();
    }

    @Override
    public void onComplete() {
        mView.finishLoading();
    }
}
