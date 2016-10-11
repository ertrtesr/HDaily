package com.hwj.hdaily.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.ColumnHolder;
import com.hwj.hdaily.model.entity.ColumnInfo;
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

public class ColumnAdapter extends XRecyclerView.Adapter<ColumnHolder> {

    private List<ColumnInfo.DataBean> mColumns;

    public ColumnAdapter(List<ColumnInfo.DataBean> columns) {
        mColumns = columns;
    }

    @Override
    public ColumnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_column, parent);
        return new ColumnHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ColumnHolder holder, int position) {
        String imgUrl = mColumns.get(position).getThumbnail();
        String name = mColumns.get(position).getName();
        UIUtils.loadImage(imgUrl, holder.iv_column_item);
        if (!TextUtils.isEmpty(name)) {
            holder.tv_column_item.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return mColumns.size();
    }
}
