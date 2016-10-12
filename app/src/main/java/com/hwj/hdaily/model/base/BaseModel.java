package com.hwj.hdaily.model.base;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.model.entity.base.HttpResult;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: model的基类,子类只需复写其构造方法即可
 * <p>
 * 时间: 16/10/12
 */

public class BaseModel<T extends HttpResult> {
    protected INetCallback<T> mCallback;

    public BaseModel(INetCallback<T> callback) {
        mCallback = callback;
    }
}
