package com.hwj.hdaily.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.base.BaseHolder;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class ColumnHolder extends BaseHolder {

    @BindView(R.id.iv_column_item)
    public ImageView iv_column_item;

    @BindView(R.id.tv_column_item)
     public TextView tv_column_item;

    public ColumnHolder(View itemView) {
        super(itemView);
    }
}
