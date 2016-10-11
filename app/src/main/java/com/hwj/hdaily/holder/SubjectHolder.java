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

public class SubjectHolder extends BaseHolder {

    @BindView(R.id.iv_subject_item)
    public ImageView iv_subject_item;

    @BindView(R.id.tv_subject_item)
    public TextView tv_subject_item;

    public SubjectHolder(View itemView) {
        super(itemView);
    }
}
