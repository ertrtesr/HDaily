package com.hwj.hdaily.model.impl;

import com.hwj.hdaily.api.API;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.manager.FragmentCollection;
import com.hwj.hdaily.model.base.BaseModel;
import com.hwj.hdaily.model.entity.ColumnInfo;
import com.hwj.hdaily.model.interfaces.IColumnModel;
import com.hwj.hdaily.view.fragment.ColumnFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class ColumnModel extends BaseModel<ColumnInfo> implements IColumnModel {

    public ColumnModel(INetCallback<ColumnInfo> callback) {
        super(callback);
    }

    @Override
    public void getColumnInfo() {
        API.doApi(API.service().getColumnInfo(),
                new BaseSubscriber(mCallback),
                FragmentCollection.getFragment(ColumnFragment.class));
    }
}
