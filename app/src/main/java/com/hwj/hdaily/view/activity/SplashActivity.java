package com.hwj.hdaily.view.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.model.entity.SplashInfo;
import com.hwj.hdaily.presenter.impl.SplashPresenter;
import com.hwj.hdaily.utils.UIUtils;
import com.hwj.hdaily.view.interfaces.ISplashView;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/8
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashView {

    @BindView(R.id.iv_splash)
    ImageView iv_splash;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {    //获取数据
        mPresenter.getSplashInfo();
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showSplash(SplashInfo splashInfo) {
        UIUtils.loadImage(splashInfo.getImg(), iv_splash);

        //放大的动画
        iv_splash.animate().scaleX(1.2f).scaleY(1.2f).setDuration(2000).setStartDelay(300).start();

        //延迟两秒进入主界面
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
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
    }
}
