package com.hwj.hdaily.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.utils.LogUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/14
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.met_username)
    MaterialEditText met_username;

    @BindView(R.id.met_password)
    MaterialEditText met_password;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.btn_login)
    Button btn_login;

    private String mUsername;
    private String mPassword;

    @Override
    protected void initInject() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        mUsername = met_username.getText().toString().trim();
        mPassword = met_password.getText().toString().trim();
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initListener() {
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if (!TextUtils.isEmpty(mUsername) && !TextUtils.isEmpty(mPassword)) {
                    try {
                        EMClient.getInstance().createAccount(mUsername, mPassword);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_login:
                //登录
                EMClient.getInstance().login(mUsername, mPassword, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().chatManager().loadAllConversations();
                    }

                    @Override
                    public void onError(int code, String status) {
                        LogUtils.i(getClass().getSimpleName(), status);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
