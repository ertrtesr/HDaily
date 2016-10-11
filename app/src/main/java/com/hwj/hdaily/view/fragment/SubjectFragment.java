package com.hwj.hdaily.view.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.hwj.hdaily.R;
import com.hwj.hdaily.adapter.SubjectAdapter;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.model.entity.SubjectInfo;
import com.hwj.hdaily.presenter.impl.SubjectPresenter;
import com.hwj.hdaily.utils.UIUtils;
import com.hwj.hdaily.view.interfaces.ISubjectView;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
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

public class SubjectFragment extends BaseFragment<SubjectPresenter> implements ISubjectView {

    @BindView(R.id.rv_subject)
    XRecyclerView rv_subject;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subject;
    }

    @Override
    protected void AfterActivityCreated() {
        initData();
    }

    private void initData() {
        mPresenter.getSubjectInfo();        //获取网络数据

        rv_subject.setLoadingMoreEnabled(false);
        rv_subject.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrowRefreshHeader header = new ArrowRefreshHeader(getActivity());
        header.setProgressStyle(ProgressStyle.BallSpinFadeLoader);
        header.setArrowImageView(R.mipmap.down_arrow);
        header.setVisiableHeight(UIUtils.dip2Px(10));
//        rv_subject.addHeaderView(header);
        rv_subject.setRefreshHeader(header);
        rv_subject.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSubjectInfo();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    /**
     * 展示数据
     *
     * @param subjectInfo
     */
    @Override
    public void showSubject(SubjectInfo subjectInfo) {
        rv_subject.setAdapter(new SubjectAdapter(subjectInfo.getOthers()));
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
        rv_subject.refreshComplete();
    }
}
