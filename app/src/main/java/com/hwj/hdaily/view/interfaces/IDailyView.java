package com.hwj.hdaily.view.interfaces;

import com.hwj.hdaily.base.interfaces.IBaseView;
import com.hwj.hdaily.model.entity.DailyInfo;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public interface IDailyView extends IBaseView {
    void showDaily(DailyInfo dailyInfo);
}
