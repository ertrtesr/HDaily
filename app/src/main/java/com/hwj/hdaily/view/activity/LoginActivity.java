package com.hwj.hdaily.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.conf.Constants;
import com.hwj.hdaily.utils.LogUtils;
import com.hwj.hdaily.utils.SPUtils;
import com.hwj.hdaily.utils.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
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

    }

    @Override
    protected void initView() {
        String username = SPUtils.getString(Constants.USERNAME);
        String password = SPUtils.getString(Constants.PASSWORD);
        if (!TextUtils.isEmpty(username)) {
            met_username.setText(username);
            met_username.setSelection(username.length());
        }
        if (!TextUtils.isEmpty(password)) {
            met_password.setText(password);
        }
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
                setUsernameAndPassword();
                if (!TextUtils.isEmpty(mUsername) && !TextUtils.isEmpty(mPassword)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(mUsername, mPassword);
                                saveUsernameAndPassword();
                            } catch (final HyphenateException e) {
                                int errorCode = e.getErrorCode();
                                if (errorCode == EMError.NETWORK_ERROR) {
                                    ToastUtils.showToast("网络连接异常,请重试");
                                } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                    ToastUtils.showToast("用户已存在");
                                } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                    ToastUtils.showToast("用户授权失败");
                                } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                    ToastUtils.showToast("用户非法");
                                } else {
                                    ToastUtils.showToast("其他原因");
                                }
                            }
                        }
                    }).start();
                }
                break;
            case R.id.btn_login:
                setUsernameAndPassword();
                //登录
                EMClient.getInstance().login(mUsername, mPassword, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        ToastUtils.showToast("登录成功");
                        saveUsernameAndPassword();
//                        EMClient.getInstance().chatManager().loadAllConversations();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(int code, String status) {
                        ToastUtils.showToast("登录失败");
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

    /**
     * 设置用户名和密码
     */
    private void setUsernameAndPassword() {
        mUsername = met_username.getText().toString().trim();
        mPassword = met_password.getText().toString().trim();
    }

    /**
     * 保存用户名和密码
     */
    private void saveUsernameAndPassword() {
        SPUtils.putString(Constants.USERNAME, mUsername);
        SPUtils.putString(Constants.PASSWORD, mPassword);
    }
}
