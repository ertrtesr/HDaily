package com.hwj.hdaily.view.interfaces;

import com.hwj.hdaily.base.interfaces.IBaseView;
import com.hwj.hdaily.model.entity.ColumnInfo;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public interface IColumnView extends IBaseView {
    void showColumn(ColumnInfo columnInfo);
}
