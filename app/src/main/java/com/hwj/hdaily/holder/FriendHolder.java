package com.hwj.hdaily.holder;

import android.view.View;
import android.widget.TextView;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.base.BaseHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/17
 */

public class FriendHolder extends BaseHolder {
    @BindView(R.id.tv_item_friend)
    public TextView tv_item_friend;

    @BindView(R.id.iv_item_friend)
    public CircleImageView iv_item_friend;

    public FriendHolder(View itemView) {
        super(itemView);
    }
}
