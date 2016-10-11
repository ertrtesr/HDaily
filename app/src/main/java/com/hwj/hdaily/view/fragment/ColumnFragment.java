package com.hwj.hdaily.view.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.hwj.hdaily.R;
import com.hwj.hdaily.adapter.ColumnAdapter;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.model.entity.ColumnInfo;
import com.hwj.hdaily.presenter.impl.ColumnPresenter;
import com.hwj.hdaily.view.interfaces.IColumnView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class ColumnFragment extends BaseFragment<ColumnPresenter> implements IColumnView {

    @BindView(R.id.rv_column)
    XRecyclerView rv_column;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_column;
    }

    @Override
    protected void AfterActivityCreated() {
        initData();
    }

    private void initData() {
        mPresenter.getColumnInfo();

        rv_column.setLoadingMoreEnabled(false);
        rv_column.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv_column.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        rv_column.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getColumnInfo();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void showColumn(ColumnInfo columnInfo) {
        rv_column.setAdapter(new ColumnAdapter(columnInfo.getData()));
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void finishLoading() {
        rv_column.refreshComplete();
    }
}
