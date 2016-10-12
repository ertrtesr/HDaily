package com.hwj.hdaily.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.base.BaseApplication;
import com.hwj.hdaily.factory.FragmentFactory;
import com.hwj.hdaily.utils.UIUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar_home)
    Toolbar toolbar_home;

    @BindView(R.id.mi_home)
    MagicIndicator mi_home;

    @BindView(R.id.vp_home)
    ViewPager vp_home;

    private String[] mTitles = {"日报", "主题", "专栏", "热门"};
    private int mCurPosition;

    @Override
    protected void initInject() {
//        getActivityComponent().inject(this);          //根据情况,如果不需要注入presenter这里就空着不要写
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
    }

    @Override
    protected void initView() {
        toolbar_home.setTitle("豪老司机日报");
        toolbar_home.setLogo(R.drawable.ab_android);
        setSupportActionBar(toolbar_home);

        initViewPager();                    //初始化ViewPager
        initMagicIndicator();
    }

    private void initViewPager() {
        vp_home.setOffscreenPageLimit(3);
        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        });

        vp_home.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurPosition = position;
            }
        });
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                SimplePagerTitleView titleView = new ColorTransitionPagerTitleView(context);
                titleView.setText(mTitles[i]);
                titleView.setNormalColor(Color.parseColor("#CCCCCC"));
                titleView.setSelectedColor(Color.WHITE);
                titleView.setTextSize(18);
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_home.setCurrentItem(i);
                    }
                });
                titleView.setWidth(UIUtils.dip2Px(80));
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.WHITE);
                indicator.setLineHeight(UIUtils.dip2Px(4));
                return indicator;
            }
        });

        mi_home.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mi_home, vp_home);
    }

    @Override
    public void initListener() {
        toolbar_home.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_mail:
                        Intent intent = new Intent(HomeActivity.this, MailActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        showExitDialog();       //显示退出应用的对话框
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗?");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseApplication.exitApp();
            }
        });
        builder.show();
    }

    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
