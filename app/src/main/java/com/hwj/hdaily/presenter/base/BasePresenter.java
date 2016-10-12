package com.hwj.hdaily.presenter.base;

import com.hwj.hdaily.base.interfaces.IBaseView;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.di.component.DaggerPresenterComponent;
import com.hwj.hdaily.di.component.PresenterComponent;
import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.model.entity.base.HttpResult;

import javax.inject.Inject;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: Presenter的基类,子类只需复写构造方法和initInject()方法
 * <p>
 * 时间: 16/10/12
 */

public abstract class BasePresenter<T, R extends IBaseView> implements INetCallback<HttpResult> {

    @Inject
    protected T mModel;

    protected R mView;

    public BasePresenter(R view) {
        mView = view;
        initInject();
    }

    protected PresenterComponent getPresenterComponent() {
        return DaggerPresenterComponent.builder().modelModule(new ModelModule(this)).build();
    }

    protected abstract void initInject();
}
