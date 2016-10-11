package com.hwj.hdaily.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.DailyHolder;
import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.utils.UIUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class DailyAdapter extends XRecyclerView.Adapter<DailyHolder> {

    private List<DailyInfo.StoriesBean> mStories;

    public DailyAdapter() {
    }

    public DailyAdapter(List<DailyInfo.StoriesBean> stories) {
        this.mStories = stories;
    }

    @Override
    public DailyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_daily = UIUtils.inflate(R.layout.item_daily, parent);         //parent就是recyclerview
        return new DailyHolder(item_daily);
    }

    @Override
    public void onBindViewHolder(DailyHolder holder, int position) {
        if (mStories != null && mStories.size() > 0) {
            DailyInfo.StoriesBean info = mStories.get(position);
            String imgUrl = info.getImages().get(0);
            String title = info.getTitle();
            UIUtils.loadImage(imgUrl, holder.iv_daily_item_image);
            holder.tv_daily_item_title.setText(title);
        }
    }

    @Override
    public int getItemCount() {
        if (mStories != null) {
            return mStories.size();
        }
        return 0;
    }
}
