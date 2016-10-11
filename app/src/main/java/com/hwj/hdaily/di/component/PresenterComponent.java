package com.hwj.hdaily.di.component;

import com.hwj.hdaily.di.module.ModelModule;
import com.hwj.hdaily.presenter.impl.ColumnPresenter;
import com.hwj.hdaily.presenter.impl.DailyPresenter;
import com.hwj.hdaily.presenter.impl.HotPresenter;
import com.hwj.hdaily.presenter.impl.SplashPresenter;
import com.hwj.hdaily.presenter.impl.SubjectPresenter;

import dagger.Component;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 将model实体注入到Presenter中的注入器
 * <p>
 * 时间: 16/10/9
 */
@Component(modules = ModelModule.class)
public interface PresenterComponent {
    void inject(SplashPresenter presenter);

    void inject(DailyPresenter presenter);

    void inject(SubjectPresenter presenter);

    void inject(ColumnPresenter presenter);

    void inject(HotPresenter presenter);
}
