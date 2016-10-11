package com.hwj.hdaily.base.interfaces;

import com.hwj.hdaily.model.entity.base.HttpResult;

/**
 * 作者: huangwenjian
 * -
 * 描述: 网络回调的接口
 * -
 * 日期: 16/8/23
 */
public interface INetCallback<T extends HttpResult> {

    void onStart();

    void onSuccess(T t);

    void onError(String e);

    void onComplete();
}

