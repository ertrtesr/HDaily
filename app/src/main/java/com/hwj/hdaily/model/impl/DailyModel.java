package com.hwj.hdaily.model.impl;

import com.hwj.hdaily.api.API;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.manager.FragmentCollection;
import com.hwj.hdaily.model.base.BaseModel;
import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.model.interfaces.IDailyModel;
import com.hwj.hdaily.view.fragment.DailyFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class DailyModel extends BaseModel<DailyInfo> implements IDailyModel {

    public DailyModel(INetCallback<DailyInfo> callback) {
        super(callback);
    }

    @Override
    public void getDailyInfo() {
        API.doApi(API.service().getDailyInfo(),
                new BaseSubscriber(mCallback),
                FragmentCollection.getFragment(DailyFragment.class));
    }
}
