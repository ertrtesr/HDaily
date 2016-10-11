package com.hwj.hdaily.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.SubjectHolder;
import com.hwj.hdaily.model.entity.SubjectInfo;
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

public class SubjectAdapter extends XRecyclerView.Adapter<SubjectHolder> {

    private List<SubjectInfo.OthersBean> mSubjects;

    public SubjectAdapter(List<SubjectInfo.OthersBean> subjects) {
        this.mSubjects = subjects;
    }

    @Override
    public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_subject, parent);
        return new SubjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubjectHolder holder, int position) {
        String imgUrl = mSubjects.get(position).getThumbnail();
        String name = mSubjects.get(position).getName();
        UIUtils.loadImage(imgUrl, holder.iv_subject_item);
        holder.tv_subject_item.setText(name);
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }
}
