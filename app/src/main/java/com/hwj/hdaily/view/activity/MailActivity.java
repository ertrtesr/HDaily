package com.hwj.hdaily.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/12
 */

public class MailActivity extends BaseActivity {
    @BindView(R.id.toolbar_mail)
    Toolbar toolbar_mail;

    @BindView(R.id.met_send_address)
    MaterialEditText met_send_address;

    @BindView(R.id.met_my_address)
    MaterialEditText met_my_address;

    @BindView(R.id.met_mail_content)
    MaterialEditText met_mail_content;

    @Override
    protected void initInject() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mail;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar_mail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_mail_clear:
                met_mail_content.setText("");
                return true;
            case android.R.id.home:
                System.out.println("home");
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
