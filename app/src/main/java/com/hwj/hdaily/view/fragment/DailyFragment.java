package com.hwj.hdaily.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hwj.hdaily.R;
import com.hwj.hdaily.adapter.DailyAdapter;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.base.listener.RecyclerViewClickListener;
import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.presenter.impl.DailyPresenter;
import com.hwj.hdaily.view.interfaces.IDailyView;
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

public class DailyFragment extends BaseFragment<DailyPresenter> implements IDailyView {

    @BindView(R.id.rv_daily)
    XRecyclerView rv_daily;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void AfterActivityCreated() {
        initData();
    }

    private void initData() {
        mPresenter.getDailyInfo();
        initRecyclerView();
    }

    private void initRecyclerView() {
        rv_daily.setLoadingMoreEnabled(false);
        rv_daily.addFootView(new View(getContext()));       //必须要有这句,否则底部会留白
        rv_daily.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv_daily.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_daily.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDailyInfo();
            }

            @Override
            public void onLoadMore() {

            }
        });

        rv_daily.addOnItemTouchListener(new RecyclerViewClickListener(rv_daily,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
    }

    @Override
    public void showDaily(DailyInfo dailyInfo) {
        rv_daily.setAdapter(new DailyAdapter(dailyInfo.getStories()));
    }

    @Override
    public void showLoading() {
        //显示加载进度条
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void finishLoading() {
        //结束加载进度条
        rv_daily.refreshComplete();
    }
}
