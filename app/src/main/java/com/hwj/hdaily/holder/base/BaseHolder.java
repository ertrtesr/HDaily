package com.hwj.hdaily.holder.base;

import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class BaseHolder extends XRecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
