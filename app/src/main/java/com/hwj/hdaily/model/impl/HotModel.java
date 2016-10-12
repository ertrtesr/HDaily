package com.hwj.hdaily.model.impl;

import com.hwj.hdaily.api.API;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.manager.FragmentCollection;
import com.hwj.hdaily.model.base.BaseModel;
import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.model.interfaces.IHotModel;
import com.hwj.hdaily.view.fragment.HotFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class HotModel extends BaseModel<HotInfo> implements IHotModel {

    public HotModel(INetCallback<HotInfo> callback) {
        super(callback);
    }

    @Override
    public void getHotInfo() {
        API.doApi(API.service().getHotInfo(),
                new BaseSubscriber(mCallback),
                FragmentCollection.getFragment(HotFragment.class));
    }
}
