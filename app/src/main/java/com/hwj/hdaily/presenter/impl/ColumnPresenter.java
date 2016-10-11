package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.ColumnInfo;
import com.hwj.hdaily.model.interfaces.IColumnModel;
import com.hwj.hdaily.presenter.interfaces.IColumnPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.IColumnView;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class ColumnPresenter implements INetCallback<ColumnInfo>, IColumnPresenter {

    @Inject
    IColumnModel mColumnModel;

    IColumnView mColumnView;

    public ColumnPresenter(IColumnView columnView) {
        mColumnView = columnView;
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
    public void getColumnInfo() {
        mColumnModel.getColumnInfo();
    }

    @Override
    public void onStart() {
        mColumnView.showLoading();
    }

    @Override
    public void onSuccess(ColumnInfo columnInfo) {
        mColumnView.showColumn(columnInfo);
    }

    @Override
    public void onError(String e) {
        mColumnView.showError();
        LogUtils.i(getClass().getSimpleName(), e);
    }

    @Override
    public void onComplete() {
        mColumnView.finishLoading();
    }
}
