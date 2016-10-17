package com.hwj.hdaily.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hwj.hdaily.R;
import com.hwj.hdaily.adapter.FriendAdapter;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.base.listener.RecyclerViewClickListener;
import com.hwj.hdaily.manager.UserCreator;
import com.hwj.hdaily.utils.UIUtils;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 好友页面
 * <p>
 * 时间: 16/10/14
 */

public class FriendActivity extends BaseActivity {
    @BindView(R.id.toolbar_friend)
    Toolbar toolbar_friend;

    @BindView(R.id.srl_friend)
    SwipeRefreshLayout srl_friend;

    @BindView(R.id.rv_friend)
    RecyclerView rv_friend;

    @Override
    protected void initInject() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_friend;
    }

    @Override
    public void initData() {
        setSupportActionBar(toolbar_friend);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initView() {
        //设置toolbar
        rv_friend.setLayoutManager(new LinearLayoutManager(this));
        FriendAdapter friendAdapter = new FriendAdapter(UserCreator.getAllUsers());
        rv_friend.setAdapter(friendAdapter);

        srl_friend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                UIUtils.postTaskDelay(new Runnable() {
                    @Override
                    public void run() {
                        srl_friend.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initListener() {
        rv_friend.addOnItemTouchListener(new RecyclerViewClickListener(rv_friend,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:         //点击了返回键
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
