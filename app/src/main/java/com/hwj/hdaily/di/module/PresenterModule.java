package com.hwj.hdaily.di.module;

import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.presenter.impl.ColumnPresenter;
import com.hwj.hdaily.presenter.impl.DailyPresenter;
import com.hwj.hdaily.presenter.impl.HotPresenter;
import com.hwj.hdaily.presenter.impl.SplashPresenter;
import com.hwj.hdaily.presenter.impl.SubjectPresenter;
import com.hwj.hdaily.view.activity.SplashActivity;
import com.hwj.hdaily.view.fragment.ColumnFragment;
import com.hwj.hdaily.view.fragment.DailyFragment;
import com.hwj.hdaily.view.fragment.HotFragment;
import com.hwj.hdaily.view.fragment.SubjectFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 提供Presenter的实例,为了注入到Activity或Fragment中
 * <p>
 * 时间: 16/10/9
 */
@Module
public class PresenterModule {
    private BaseActivity mActivity;
    private BaseFragment mFragment;

    public PresenterModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    public PresenterModule(BaseFragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter((SplashActivity) mActivity);
    }

    @Provides
    DailyPresenter provideDailyPresenter() {
        return new DailyPresenter((DailyFragment) mFragment);
    }

    @Provides
    SubjectPresenter provideSubjectPresenter() {
        return new SubjectPresenter((SubjectFragment) mFragment);
    }

    @Provides
    ColumnPresenter provideColumnPresenter() {
        return new ColumnPresenter((ColumnFragment) mFragment);
    }

    @Provides
    HotPresenter provideHotPresenter() {
        return new HotPresenter((HotFragment) mFragment);
    }
}
