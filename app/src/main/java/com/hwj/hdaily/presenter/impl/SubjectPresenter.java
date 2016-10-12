package com.hwj.hdaily.presenter.impl;

import com.hwj.hdaily.model.entity.SubjectInfo;
import com.hwj.hdaily.model.entity.base.HttpResult;
import com.hwj.hdaily.model.interfaces.ISubjectModel;
import com.hwj.hdaily.presenter.base.BasePresenter;
import com.hwj.hdaily.presenter.interfaces.ISubjectPresenter;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.view.interfaces.ISubjectView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class SubjectPresenter extends BasePresenter<ISubjectModel, ISubjectView> implements ISubjectPresenter {

    public SubjectPresenter(ISubjectView view) {
        super(view);
    }

    @Override
    public void getSubjectInfo() {
        mModel.getSubjectInfo();
    }

    @Override
    public void initInject() {
        getPresenterComponent().inject(this);
    }

    @Override
    public void onStart() {
        mView.showLoading();
    }

    @Override
    public void onSuccess(HttpResult result) {
        if (result instanceof SubjectInfo)
            mView.showSubject((SubjectInfo) result);
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
