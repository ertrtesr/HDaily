package com.hwj.hdaily.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.hwj.hdaily.base.interfaces.IHandler;
import com.hwj.hdaily.di.component.ActivityComponent;
import com.hwj.hdaily.di.component.DaggerActivityComponent;
import com.hwj.hdaily.di.module.PresenterModule;
import com.hwj.hdaily.manager.ActivityCollection;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/8
 * <p>
 * 描述:
 */
public abstract class BaseActivity<T> extends RxAppCompatActivity {
    protected UIHandler mAHandler = new UIHandler();

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initRootView();
        initData();
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAHandler != null) {
            mAHandler.removeCallbacksAndMessages(null);
            mAHandler = null;
        }
        ActivityCollection.removeActivity(this);     //从静态集合中移除
    }

    public void initHandler() {
        mAHandler.setHandler(new IHandler() {
            @Override
            public void handleMessage(Message msg) {   //只要收到UIHandler发送的消息,这个方法里的代码就会执行
                processMsg(msg);
            }
        });
    }

    public void init() {
        initHandler();                              //设置handler
        initInject();
        ActivityCollection.addActivity(this);       //添加到静态map中
    }

    public void initRootView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .presenterModule(new PresenterModule(this))
                .build();
    }

    protected abstract void initInject();

    public abstract int getLayoutId();

    public abstract void initData();

    protected abstract void initView();

    public abstract void initListener();

    /**
     * 处理消息的方法,让子类实现
     *
     * @param msg
     */
    public void processMsg(Message msg) {

    }
}
