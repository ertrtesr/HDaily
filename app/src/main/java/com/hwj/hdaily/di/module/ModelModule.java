package com.hwj.hdaily.di.module;

import com.hwj.hdaily.base.interfaces.INetCallback;
import com.hwj.hdaily.model.impl.ColumnModel;
import com.hwj.hdaily.model.impl.DailyModel;
import com.hwj.hdaily.model.impl.HotModel;
import com.hwj.hdaily.model.impl.SplashModel;
import com.hwj.hdaily.model.impl.SubjectModel;
import com.hwj.hdaily.model.interfaces.IColumnModel;
import com.hwj.hdaily.model.interfaces.IDailyModel;
import com.hwj.hdaily.model.interfaces.IHotModel;
import com.hwj.hdaily.model.interfaces.ISplashModel;
import com.hwj.hdaily.model.interfaces.ISubjectModel;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 提供MVP中的model实体,为了注入到Presenter类中
 * <p>
 * 时间: 16/10/9
 */

@Module
public class ModelModule {
    INetCallback mCallback;

    public ModelModule(INetCallback callback) {
        this.mCallback = callback;
    }

    @Provides
    public ISplashModel provideSplashModel() {
        return new SplashModel(mCallback);
    }

    @Provides
    public IDailyModel provideDailyModel() {
        return new DailyModel(mCallback);
    }

    @Provides
    public ISubjectModel provideSubjectModel() {
        return new SubjectModel(mCallback);
    }

    @Provides
    public IColumnModel provideColumnModel() {
        return new ColumnModel(mCallback);
    }

    @Provides
    public IHotModel provideHotModel() {
        return new HotModel(mCallback);
    }
}
