package com.hwj.hdaily.holder;

import android.view.View;
import android.widget.TextView;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.base.BaseHolder;
import com.hwj.hdaily.widget.SquareImageView;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class DailyHolder extends BaseHolder {

    @BindView(R.id.iv_daily_item_image)
    public SquareImageView iv_daily_item_image;

    @BindView(R.id.tv_daily_item_title)
    public TextView tv_daily_item_title;

    public DailyHolder(View itemView) {
        super(itemView);
    }
}
