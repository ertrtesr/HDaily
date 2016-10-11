package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.SubjectInfo;
import com.hwj.hdaily.model.interfaces.ISubjectModel;
import com.hwj.hdaily.presenter.interfaces.ISubjectPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.ISubjectView;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class SubjectPresenter implements INetCallback<SubjectInfo>, ISubjectPresenter {

    private ISubjectView mSubjectView;

    @Inject
    ISubjectModel mSubjectModel;

    public SubjectPresenter(ISubjectView subjectView) {
        this.mSubjectView = subjectView;
        initInject();           //不要忘了这句
    }

    @Override
    public void getSubjectInfo() {
        mSubjectModel.getSubjectInfo();
    }

    @Override
    public void initInject() {
        DaggerPresenterComponent.builder().modelModule(new ModelModule(this)).build().inject(this);
    }

    @Override
    public void onStart() {
        mSubjectView.showLoading();
    }

    @Override
    public void onSuccess(SubjectInfo subjectInfo) {
        mSubjectView.showSubject(subjectInfo);
    }

    @Override
    public void onError(String e) {
        LogUtils.i(getClass().getSimpleName(), e);
        mSubjectView.showError();
    }

    @Override
    public void onComplete() {
        mSubjectView.finishLoading();
    }
}
