package com.hwj.hdaily.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.base.interfaces.IHandler;
import com.hwj.hdaily.di.component.DaggerFragmentComponent;
import com.hwj.hdaily.di.component.FragmentComponent;
import com.hwj.hdaily.di.module.PresenterModule;
import com.hwj.hdaily.manager.FragmentCollection;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/2
 */
public abstract class BaseFragment<T> extends RxFragment {

    protected UIHandler mFHandler = new UIHandler();       //初始化handler对象

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentCollection.addFragment(this);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AfterActivityCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFHandler != null) {
            mFHandler.removeCallbacksAndMessages(null);
            mFHandler = null;
        }
    }

    protected void init() {
        mFHandler.setHandler(new IHandler() {
            @Override
            public void handleMessage(Message message) {
                processMsg(message);
            }
        });
        initInject();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .presenterModule(new PresenterModule(this))
                .build();
    }

    protected abstract void initInject();

    protected void processMsg(Message message) {

    }

//    protected abstract View initRootView(LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract int getLayoutId();

    protected abstract void AfterActivityCreated();
}
