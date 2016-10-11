package com.hwj.hdaily.di.component;

import com.hwj.hdaily.di.annotation.FragmentScope;
import com.hwj.hdaily.di.module.PresenterModule;
import com.hwj.hdaily.view.fragment.ColumnFragment;
import com.hwj.hdaily.view.fragment.DailyFragment;
import com.hwj.hdaily.view.fragment.HotFragment;
import com.hwj.hdaily.view.fragment.SubjectFragment;

import dagger.Component;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 将Presenter实体注入到Fragment中
 * <p>
 * 时间: 16/10/10
 */

@FragmentScope
@Component(modules = PresenterModule.class)
public interface FragmentComponent {
    void inject(DailyFragment fragment);

    void inject(SubjectFragment fragment);

    void inject(ColumnFragment fragment);

    void inject(HotFragment fragment);
}
