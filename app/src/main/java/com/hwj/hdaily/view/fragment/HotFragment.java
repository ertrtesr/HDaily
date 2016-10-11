package com.hwj.hdaily.view.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.hwj.hdaily.R;
import com.hwj.hdaily.adapter.HotAdapter;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.presenter.impl.HotPresenter;
import com.hwj.hdaily.view.interfaces.IHotView;
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

public class HotFragment extends BaseFragment<HotPresenter> implements IHotView {

    @BindView(R.id.rv_hot)
    XRecyclerView rv_hot;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void AfterActivityCreated() {
        initData();
    }

    private void initData() {
        mPresenter.getHotInfo();

        rv_hot.setLoadingMoreEnabled(false);
        rv_hot.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv_hot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_hot.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHotInfo();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void showHot(HotInfo hotInfo) {
        rv_hot.setAdapter(new HotAdapter(hotInfo.getRecent()));
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
        rv_hot.refreshComplete();
    }
}
