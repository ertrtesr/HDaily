package com.hwj.hdaily.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.HotHolder;
import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.utils.UIUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/11
 */

public class HotAdapter extends XRecyclerView.Adapter<HotHolder> {

    private List<HotInfo.RecentBean> mHots;

    public HotAdapter(List<HotInfo.RecentBean> hots) {
        mHots = hots;
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_hot, parent);
        return new HotHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotHolder holder, int position) {
        String imgUrl = mHots.get(position).getThumbnail();
        String title = mHots.get(position).getTitle();
        UIUtils.loadImage(imgUrl, holder.iv_hot_item_image);
        holder.tv_hot_item_title.setText(title);
    }

    @Override
    public int getItemCount() {
        return mHots.size();
    }
}
