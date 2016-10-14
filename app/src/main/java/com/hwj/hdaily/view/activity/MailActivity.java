package com.hwj.hdaily.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.manager.MailSender;
import com.hwj.hdaily.model.entity.MailSenderInfo;
import com.hwj.hdaily.utils.ToastUtils;
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
    protected void initView() {
        setSupportActionBar(toolbar_mail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initData() {
    }

    private void sendMail() {
        final MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort("587");
        mailInfo.setValidate(true);                         //设置是否需要身份验证
        mailInfo.setUserName("631236933@qq.com");
        mailInfo.setPassword("xjetsrosirsqbeah ");          //您的邮箱密码,QQ邮箱是填授权码
        mailInfo.setFromAddress("631236933@qq.com");
        mailInfo.setSubject("测试1");
        mailInfo.setContent("内容1");
        mailInfo.setReceivers(new String[]{"er1trtesr2009@163.com"});       //设置接收者
        mailInfo.setCcs(new String[]{"er1trtesr2009@163.com"});             //设置抄送者

        //发送邮件是网络操作,要在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isSuccess = new MailSender().sendTextMail(mailInfo);
                if (isSuccess) {
                    System.out.println(Thread.currentThread().getName());
                    ToastUtils.showToast("发送成功");
                } else {
                    ToastUtils.showToast("发送失败");
                }
            }
        }).start();
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
            case R.id.menu_mail_send:
                sendMail();
                return true;
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
