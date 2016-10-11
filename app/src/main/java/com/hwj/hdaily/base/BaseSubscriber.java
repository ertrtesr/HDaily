package com.hwj.hdaily.base;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.model.entity.base.HttpResult;

import rx.Subscriber;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class BaseSubscriber<T extends HttpResult> extends Subscriber<T> {
    private INetCallback<T> mCallback;

    public BaseSubscriber() {

    }

    public BaseSubscriber(INetCallback<T> callback) {
        mCallback = callback;
    }

    @Override
    public void onStart() {
        mCallback.onStart();
    }

    @Override
    public void onNext(T t) {
        mCallback.onSuccess(t);        //跳转到WeatherPresenterImpl类中的onResponse()方法
    }

    @Override
    public void onError(Throwable e) {
        mCallback.onError(e.getMessage());
    }

    @Override
    public void onCompleted() {
        mCallback.onComplete();
    }
}
