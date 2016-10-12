package com.hwj.hdaily.model.impl;

import com.hwj.hdaily.api.API;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.manager.FragmentCollection;
import com.hwj.hdaily.model.base.BaseModel;
import com.hwj.hdaily.model.entity.SubjectInfo;
import com.hwj.hdaily.model.interfaces.ISubjectModel;
import com.hwj.hdaily.view.fragment.SubjectFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class SubjectModel extends BaseModel<SubjectInfo> implements ISubjectModel {

    public SubjectModel(INetCallback<SubjectInfo> callback) {
        super(callback);
    }

    @Override
    public void getSubjectInfo() {
        //真正请求网络的操作
        API.doApi(API.service().getSubjectInfo(),
                new BaseSubscriber(mCallback),
                FragmentCollection.getFragment(SubjectFragment.class));
    }
}
