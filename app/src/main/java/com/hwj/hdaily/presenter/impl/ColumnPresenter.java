package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.model.entity.ColumnInfo;
import com.hwj.hdaily.model.entity.base.HttpResult;
import com.hwj.hdaily.model.interfaces.IColumnModel;
import com.hwj.hdaily.presenter.interfaces.BasePresenter;
import com.hwj.hdaily.presenter.interfaces.IColumnPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IColumnView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class ColumnPresenter extends BasePresenter<IColumnModel, IColumnView> implements IColumnPresenter {

    public ColumnPresenter(IColumnView view) {
        super(view);
    }

    @Override
    public void initInject() {
        getPresentComponent().inject(this);
    }

    @Override
    public void getColumnInfo() {
        mModel.getColumnInfo();
    }

    @Override
    public void onStart() {
        mView.showLoading();
    }

    @Override
    public void onSuccess(HttpResult result) {
        if (result instanceof ColumnInfo)
            mView.showColumn((ColumnInfo) result);
    }

    @Override
    public void onError(String e) {
        mView.showError();
        LogUtils.i(getClass().getSimpleName(), e);
    }

    @Override
    public void onComplete() {
        mView.finishLoading();
    }
}
